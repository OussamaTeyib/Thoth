package com.oussamateyib.thoth.core.data.repository

import com.oussamateyib.thoth.core.model.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotesStream(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note): Long

    suspend fun deleteNote(note: Note)
}