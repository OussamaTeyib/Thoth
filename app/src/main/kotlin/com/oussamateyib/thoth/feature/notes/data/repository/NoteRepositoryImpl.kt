package com.oussamateyib.thoth.feature.notes.data.repository

import com.oussamateyib.thoth.feature.notes.data.local.NoteDao
import com.oussamateyib.thoth.feature.notes.data.local.NoteEntity
import com.oussamateyib.thoth.feature.notes.data.mappers.toDomain
import com.oussamateyib.thoth.feature.notes.data.mappers.toEntity
import com.oussamateyib.thoth.feature.notes.domain.model.Note
import com.oussamateyib.thoth.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotesStream(): Flow<List<Note>> {
        return dao.getNotes().map { it.map(NoteEntity::toDomain) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.toDomain()
    }

    override suspend fun insertNote(note: Note): Long {
        return dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}