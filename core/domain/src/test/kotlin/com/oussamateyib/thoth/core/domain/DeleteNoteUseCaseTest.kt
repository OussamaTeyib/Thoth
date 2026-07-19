package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.testing.data.notesTestData
import com.oussamateyib.thoth.core.testing.repository.TestNotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DeleteNoteUseCaseTest {
    private val notesRepository = TestNotesRepository()
    private val useCase = DeleteNoteUseCase(notesRepository)
    private val notes = notesTestData

    @Test
    fun whenNoteExists_deletesNote() = runTest {
        notesRepository.sendNotes(notes)
        val noteToDelete = notes[0]

        useCase(noteToDelete)

        val currentNotes = notesRepository.getNotesStream().first()
        assertFalse(currentNotes.contains(noteToDelete))
    }

    @Test
    fun whenNoteDoesNotExist_remainsUnchanged() = runTest {
        notesRepository.sendNotes(notes)
        val nonExistentNote = notes[0].copy(id = 999L)

        useCase(nonExistentNote)

        val currentNotes = notesRepository.getNotesStream().first()
        assertTrue(currentNotes.containsAll(notes))
    }

    @Test
    fun whenRepositoryIsEmpty_remainsEmpty() = runTest {
        val anyNote = notes[0]

        useCase(anyNote)

        val currentNotes = notesRepository.getNotesStream().first()
        assertTrue(currentNotes.isEmpty())
    }
}
