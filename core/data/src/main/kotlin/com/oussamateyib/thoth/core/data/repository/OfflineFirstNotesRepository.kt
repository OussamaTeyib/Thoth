package com.oussamateyib.thoth.core.data.repository

import com.oussamateyib.thoth.core.data.model.asEntity
import com.oussamateyib.thoth.core.database.dao.NoteDao
import com.oussamateyib.thoth.core.database.model.NoteEntity
import com.oussamateyib.thoth.core.database.model.asExternalModel
import com.oussamateyib.thoth.core.model.data.Note
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class OfflineFirstNotesRepository @Inject constructor(
    private val dao: NoteDao
) : NotesRepository {
    override fun getNotesStream() = dao.getNotes().map {
        it.map(NoteEntity::asExternalModel)
    }

    override suspend fun getNoteById(id: Long) = dao.getNoteById(id)?.asExternalModel()

    override suspend fun insertNote(note: Note) = dao.insertNote(note.asEntity())

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.asEntity())
    }
}