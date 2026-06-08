package com.oussamateyib.thoth.feature.notes.impl.list

import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.model.data.Note

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder) : NoteListEvent()
    object ToggleOrderSection : NoteListEvent()
    data class DeleteNote(val note: Note) : NoteListEvent()
    object RestoreNote : NoteListEvent()
}