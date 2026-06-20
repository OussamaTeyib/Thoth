package com.oussamateyib.thoth.feature.notes.impl.list

import com.oussamateyib.thoth.core.domain.util.NoteOrder

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder) : NoteListEvent()
    object ToggleOrderSection : NoteListEvent()
    data class SelectNote(val id: Int) : NoteListEvent()
    object ClearSelection : NoteListEvent()
    object DeleteSelectedNotes : NoteListEvent()
    object RestoreDeletedNotes : NoteListEvent()
    data class ChangeColor(val color: Int) : NoteListEvent()
    object ToggleColorPicker : NoteListEvent()
}