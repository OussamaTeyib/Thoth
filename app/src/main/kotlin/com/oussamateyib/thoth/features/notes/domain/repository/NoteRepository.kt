package com.oussamateyib.thoth.features.notes.domain.repository

import com.oussamateyib.thoth.features.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotesStream(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note): Long

    suspend fun deleteNote(note: Note)
}