package com.oussamateyib.thoth.feature.notes.impl.presentation.editor

sealed class NoteEditorUiEvent {
    object NoteNotFound : NoteEditorUiEvent()
}