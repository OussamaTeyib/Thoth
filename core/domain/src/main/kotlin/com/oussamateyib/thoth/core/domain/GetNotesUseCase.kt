package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.domain.util.OrderType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesStreamUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ) = repository.getNotesStream().map { notes ->
        when (noteOrder) {
            is NoteOrder.Title -> when (noteOrder.orderType) {
                is OrderType.Ascending -> notes.sortedBy { it.title.lowercase() }
                is OrderType.Descending -> notes.sortedByDescending { it.title.lowercase() }
            }

            is NoteOrder.Date -> when (noteOrder.orderType) {
                is OrderType.Ascending -> notes.sortedBy { it.timestamp }
                is OrderType.Descending -> notes.sortedByDescending { it.timestamp }
            }

            is NoteOrder.Color -> when (noteOrder.orderType) {
                is OrderType.Ascending -> notes.sortedBy { it.color }
                is OrderType.Descending -> notes.sortedByDescending { it.color }
            }
        }
    }
}