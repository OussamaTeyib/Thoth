package com.oussamateyib.thoth.features.notes.presentation.list

import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.util.NoteOrder

sealed class ListEvent {
    data class Order(val noteOrder: NoteOrder) : ListEvent()
    object ToggleOrderSection : ListEvent()
    data class DeleteNote(val note: Note) : ListEvent()
    object RestoreNote : ListEvent()
}