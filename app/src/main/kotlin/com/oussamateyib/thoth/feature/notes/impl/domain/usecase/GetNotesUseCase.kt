package com.oussamateyib.thoth.feature.notes.impl.domain.usecase

import com.oussamateyib.thoth.feature.notes.impl.domain.model.Note
import com.oussamateyib.thoth.feature.notes.impl.domain.repository.NoteRepository
import com.oussamateyib.thoth.feature.notes.impl.domain.util.NoteOrder
import com.oussamateyib.thoth.feature.notes.impl.domain.util.OrderType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesStreamUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotesStream().map { notes ->
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