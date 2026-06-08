package com.oussamateyib.thoth.feature.notes.impl.presentation.list

import com.oussamateyib.thoth.feature.notes.impl.domain.model.Note
import com.oussamateyib.thoth.feature.notes.impl.domain.util.NoteOrder

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder) : NoteListEvent()
    object ToggleOrderSection : NoteListEvent()
    data class DeleteNote(val note: Note) : NoteListEvent()
    object RestoreNote : NoteListEvent()
}