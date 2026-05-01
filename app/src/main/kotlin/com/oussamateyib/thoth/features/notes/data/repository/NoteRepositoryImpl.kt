package com.oussamateyib.thoth.features.notes.data.repository

import com.oussamateyib.thoth.features.notes.data.local.NoteDao
import com.oussamateyib.thoth.features.notes.data.local.NoteEntity
import com.oussamateyib.thoth.features.notes.data.mappers.toDomain
import com.oussamateyib.thoth.features.notes.data.mappers.toEntity
import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { it.map(NoteEntity::toDomain) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.toDomain()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}