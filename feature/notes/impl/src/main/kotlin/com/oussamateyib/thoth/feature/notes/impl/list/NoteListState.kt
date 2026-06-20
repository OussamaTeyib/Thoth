package com.oussamateyib.thoth.feature.notes.impl.list

import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.domain.util.OrderType
import com.oussamateyib.thoth.core.model.data.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val selectedNoteIds: Set<Int> = emptySet(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val recentlyDeletedNotes: List<Note> = emptyList(),
) {
    val isSelectionMode: Boolean
        get() = selectedNoteIds.isNotEmpty()
}