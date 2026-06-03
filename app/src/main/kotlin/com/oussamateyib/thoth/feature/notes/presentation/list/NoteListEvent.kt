package com.oussamateyib.thoth.feature.notes.presentation.list

import com.oussamateyib.thoth.feature.notes.domain.model.Note
import com.oussamateyib.thoth.feature.notes.domain.util.NoteOrder

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder) : NoteListEvent()
    object ToggleOrderSection : NoteListEvent()
    data class DeleteNote(val note: Note) : NoteListEvent()
    object RestoreNote : NoteListEvent()
}