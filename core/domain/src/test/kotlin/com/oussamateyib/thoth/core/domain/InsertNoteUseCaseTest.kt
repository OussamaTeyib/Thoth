package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.testing.data.notesTestData
import com.oussamateyib.thoth.core.testing.repository.TestNotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class InsertNoteUseCaseTest {
    private val notesRepository = TestNotesRepository()
    private val useCase = InsertNoteUseCase(notesRepository)
    private val notes = notesTestData

    @Test
    fun whenNewNote_returnsNewIdAndInsertsToRepository() = runTest {
        notesRepository.sendNotes(notes)
        val newNote = notes[0].copy(id = 0L)

        val newId = useCase(newNote)

        val expectedId = (notes.maxOf { it.id }) + 1L
        assertEquals(expectedId, newId)

        val currentNotes = notesRepository.getNotesStream().first()
        assertTrue(currentNotes.contains(newNote.copy(id = expectedId)))
    }

    @Test
    fun whenExistingNote_returnsSameIdAndUpdatesRepository() = runTest {
        notesRepository.sendNotes(notes)
        val updatedNote = notes[0].copy(title = "Updated Title")

        val resultId = useCase(updatedNote)

        assertEquals(updatedNote.id, resultId)

        val currentNotes = notesRepository.getNotesStream().first()
        assertTrue(currentNotes.contains(updatedNote))
    }

    @Test
    fun whenRepositoryIsEmpty_returnsNewIdAndInsertsToRepository() = runTest {
        notesRepository.sendNotes(emptyList())
        val newNote = notes[0].copy(id = 0L)

        val newId = useCase(newNote)

        assertEquals(1L, newId)

        val currentNotes = notesRepository.getNotesStream().first()
        assertTrue(currentNotes.contains(newNote.copy(id = newId)))
    }
}
