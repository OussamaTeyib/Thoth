package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.domain.util.OrderType
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.testing.data.notesTestData
import com.oussamateyib.thoth.core.testing.repository.TestNotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetNotesStreamUseCaseTest {
    private val notesRepository = TestNotesRepository()
    private val useCase = GetNotesStreamUseCase(notesRepository)
    private val notes = notesTestData

    @Test
    fun whenNoParam_returnsNotesSortedByDateUpdatedDesc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase().first()

        assertEquals(
            notes.sortedByDescending { it.updatedAt },
            result,
        )
    }

    @Test
    fun whenTitleAsc_returnsNotesSortedByTitleAsc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.Title(OrderType.Ascending)).first()

        assertEquals(
            notes.sortedBy { it.title.lowercase() },
            result,
        )
    }

    @Test
    fun whenTitleDesc_returnsNotesSortedByTitleDesc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.Title(OrderType.Descending)).first()

        assertEquals(
            notes.sortedByDescending { it.title.lowercase() },
            result,
        )
    }

    @Test
    fun whenDateCreatedAsc_returnsNotesSortedByDateCreatedAsc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.DateCreated(OrderType.Ascending)).first()

        assertEquals(
            notes.sortedBy { it.createdAt },
            result,
        )
    }

    @Test
    fun whenDateCreatedDesc_returnsNotesSortedByDateCreatedDesc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.DateCreated(OrderType.Descending)).first()

        assertEquals(
            notes.sortedByDescending { it.createdAt },
            result,
        )
    }

    @Test
    fun whenDateUpdatedAsc_returnsNotesSortedByDateUpdatedAsc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.DateUpdated(OrderType.Ascending)).first()

        assertEquals(
            notes.sortedBy { it.updatedAt },
            result,
        )
    }

    @Test
    fun whenDateUpdatedDesc_returnsNotesSortedByDateUpdatedDesc() = runTest {
        notesRepository.sendNotes(notes)

        val result = useCase(NoteOrder.DateUpdated(OrderType.Descending)).first()

        assertEquals(
            notes.sortedByDescending { it.updatedAt },
            result,
        )
    }

    @Test
    fun whenRepositoryIsEmpty_returnsEmptyList() = runTest {
        notesRepository.sendNotes(emptyList())

        val result = useCase().first()

        assertEquals(emptyList<Note>(), result)
    }
}
