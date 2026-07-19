package com.oussamateyib.thoth.core.testing.repository

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.model.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestNotesRepository : NotesRepository {
    private val notesFlow = MutableStateFlow<List<Note>>(emptyList())

    private val currentNotes
        get() = notesFlow.value

    override fun getNotesStream() = notesFlow

    override suspend fun getNoteById(id: Long) = currentNotes.find { it.id == id }

    override suspend fun insertNote(note: Note): Long {
        // Generate a new ID if this is a new note
        val id = if (note.id == 0L) {
            (currentNotes.maxOfOrNull { it.id } ?: 0L) + 1L
        } else {
            note.id
        }

        val updatedNote = note.copy(id = id)
        notesFlow.update { notes ->
            val newList = notes.toMutableList()
            // If the note already exists, update it
            val index = newList.indexOfFirst { it.id == id }
            if (index != -1) {
                newList[index] = updatedNote
            } else {
                newList.add(updatedNote)
            }
            newList
        }
        return id
    }

    override suspend fun deleteNote(note: Note) {
        notesFlow.update { notes ->
            notes.filterNot { it.id == note.id }
        }
    }

    // A test-only API to inject notes directly
    fun sendNotes(notes: List<Note>) {
        notesFlow.value = notes
    }
}
