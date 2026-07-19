package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.testing.data.notesTestData
import com.oussamateyib.thoth.core.testing.repository.TestNotesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetNoteByIdUseCaseTest {
    private val notesRepository = TestNotesRepository()
    private val useCase = GetNoteByIdUseCase(notesRepository)
    private val notes = notesTestData

    @Test
    fun whenNoteExists_returnsNote() = runTest {
        notesRepository.sendNotes(notes)
        val targetNote = notes[0]

        val result = useCase(targetNote.id)

        assertEquals(targetNote, result)
    }

    @Test
    fun whenNoteDoesNotExist_returnsNull() = runTest {
        notesRepository.sendNotes(notes)
        val nonExistentId = 999L

        val result = useCase(nonExistentId)

        assertNull(result)
    }

    @Test
    fun whenRepositoryIsEmpty_returnsNull() = runTest {
        notesRepository.sendNotes(emptyList())
        val anyId = 1L

        val result = useCase(anyId)

        assertNull(result)
    }
}
