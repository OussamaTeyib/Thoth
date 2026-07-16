package com.oussamateyib.thoth.feature.notes.impl.list

import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.model.data.NoteColor

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder) : NoteListEvent()
    object ToggleSortSheet : NoteListEvent()
    data class SelectNote(val id: Long) : NoteListEvent()
    object ClearSelection : NoteListEvent()
    object DeleteSelectedNotes : NoteListEvent()
    object RestoreDeletedNotes : NoteListEvent()
    data class ChangeColor(val color: NoteColor) : NoteListEvent()
    object ToggleColorPicker : NoteListEvent()
}
