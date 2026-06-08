package com.oussamateyib.thoth.core.data.repository

import com.oussamateyib.thoth.core.database.dao.NoteDao
import com.oussamateyib.thoth.core.database.model.NoteEntity
import com.oussamateyib.thoth.core.database.model.asExternalModel
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.data.model.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class OfflineFirstNotesRepository @Inject constructor(
    private val dao: NoteDao
) : NotesRepository {
    override fun getNotesStream(): Flow<List<Note>> {
        return dao.getNotes().map { it.map(NoteEntity::asExternalModel) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.asExternalModel()
    }

    override suspend fun insertNote(note: Note): Long {
        return dao.insertNote(note.asEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.asEntity())
    }
}