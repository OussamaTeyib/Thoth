package com.oussamateyib.thoth.feature.notes.impl.editor

sealed class NoteEditorUiEvent {
    object NoteNotFound : NoteEditorUiEvent()
}