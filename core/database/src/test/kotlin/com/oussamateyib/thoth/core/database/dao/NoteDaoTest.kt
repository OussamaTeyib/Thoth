package com.oussamateyib.thoth.core.database.dao

import com.oussamateyib.thoth.core.database.ThothDatabaseTest
import com.oussamateyib.thoth.core.database.model.asEntity
import com.oussamateyib.thoth.core.testing.data.notesTestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [37])
class NoteDaoTest : ThothDatabaseTest() {
    @Test
    fun insertNote_retrievesNoteById() = runTest {
        val note = notesTestData.first().asEntity()
        noteDao.insertNote(note)

        val retrievedNote = noteDao.getNoteById(note.id)
        assertEquals(note, retrievedNote)
    }

    @Test
    fun getNotes_emitsAllNotes() = runTest {
        val entities = notesTestData.map { it.asEntity() }
        entities.forEach { noteDao.insertNote(it) }

        val notes = noteDao.getNotes().first()
        assertEquals(entities.size, notes.size)
        assertEquals(entities.sortedBy { it.id }, notes.sortedBy { it.id })
    }

    @Test
    fun insertNote_replacesOnConflict() = runTest {
        val note1 = notesTestData.first().asEntity().copy(title = "Original Title")
        noteDao.insertNote(note1)

        val note2 = note1.copy(title = "Updated Title")
        noteDao.insertNote(note2)

        val retrievedNote = noteDao.getNoteById(note1.id)
        assertEquals("Updated Title", retrievedNote?.title)
    }

    @Test
    fun deleteNote_removesNoteFromDatabase() = runTest {
        val note = notesTestData.first().asEntity()
        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        val retrievedNote = noteDao.getNoteById(note.id)
        assertEquals(null, retrievedNote)
    }
}
