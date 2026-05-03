package com.oussamateyib.thoth.features.notes.domain.usecase

import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import com.oussamateyib.thoth.features.notes.domain.util.NoteOrder
import com.oussamateyib.thoth.features.notes.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
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
}