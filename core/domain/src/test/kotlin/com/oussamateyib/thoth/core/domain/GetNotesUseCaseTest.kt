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
    fun whenNoParam_notesSortedByDateUpdatedDescReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase()

        assertEquals(
            notes.sortedByDescending { it.updatedAt },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByTitleAsc_notesSortedByTitleAscReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.Title(OrderType.Ascending))

        assertEquals(
            notes.sortedBy { it.title.lowercase() },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByTitleDesc_notesSortedByTitleDescReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.Title(OrderType.Descending))

        assertEquals(
            notes.sortedByDescending { it.title.lowercase() },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByDateCreatedAsc_notesSortedByDateCreatedAscReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.DateCreated(OrderType.Ascending))

        assertEquals(
            notes.sortedBy { it.createdAt },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByDateCreatedDesc_notesSortedByDateCreatedDescReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.DateCreated(OrderType.Descending))

        assertEquals(
            notes.sortedByDescending { it.createdAt },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByDateUpdatedAsc_notesSortedByDateUpdatedAscReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.DateUpdated(OrderType.Ascending))

        assertEquals(
            notes.sortedBy { it.updatedAt },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenSortedByDateUpdatedDesc_notesSortedByDateUpdatedDescReturned() = runTest {
        notesRepository.sendNotes(notes)

        val sortedNotes = useCase(NoteOrder.DateUpdated(OrderType.Descending))

        assertEquals(
            notes.sortedByDescending { it.updatedAt },
            sortedNotes.first(),
        )
    }

    @Test
    fun whenRepositoryIsEmpty_returnsEmptyList() = runTest {
        notesRepository.sendNotes(emptyList())

        val result = useCase().first()

        assertEquals(emptyList<Note>(), result)
    }
}
