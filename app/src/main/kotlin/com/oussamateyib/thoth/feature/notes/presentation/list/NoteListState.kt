package com.oussamateyib.thoth.feature.notes.presentation.list

import com.oussamateyib.thoth.feature.notes.domain.model.Note
import com.oussamateyib.thoth.feature.notes.domain.util.NoteOrder
import com.oussamateyib.thoth.feature.notes.domain.util.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    var recentlyDeletedNote: Note? = null
)