package com.oussamateyib.thoth.feature.notes.impl.list

import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.domain.util.OrderType
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.model.data.NoteColor

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val selectedNoteIds: Set<Long> = emptySet(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isSortSheetVisible: Boolean = false,
    val isColorPickerVisible: Boolean = false,
    val recentlyDeletedNotes: List<Note> = emptyList(),
) {
    val isSelectionMode: Boolean
        get() = selectedNoteIds.isNotEmpty()

    val commonSelectedColor: NoteColor?
        get() = notes
            .filter { it.id in selectedNoteIds }
            .map { it.color }
            .distinct()
            .singleOrNull()
}