package com.oussamateyib.thoth.core.testing.repository

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.model.data.Note
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestNotesRepository : NotesRepository {
    private val notesFlow = MutableSharedFlow<List<Note>>(
        // Cache the latest emitted list for new collectors
        replay = 1,
        // Discard the previous list if a new one arrives before it's collected
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    private val currentNotes
        get() = notesFlow.replayCache.firstOrNull() ?: emptyList()

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
        val newList = currentNotes.toMutableList().apply {
            // If the note already exists, update it
            val index = indexOfFirst { it.id == id }
            if (index != -1) {
                set(index, updatedNote)
            } else {
                add(updatedNote)
            }
        }
        notesFlow.tryEmit(newList)
        return id
    }

    override suspend fun deleteNote(note: Note) {
        val newList = currentNotes.filterNot { it.id == note.id }
        notesFlow.tryEmit(newList)
    }

    // A test-only API to inject notes directly
    fun sendNotes(notes: List<Note>) {
        notesFlow.tryEmit(notes)
    }
}
