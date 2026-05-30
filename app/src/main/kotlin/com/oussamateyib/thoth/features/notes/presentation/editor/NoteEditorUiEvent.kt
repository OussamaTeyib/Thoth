package com.oussamateyib.thoth.features.notes.presentation.editor

sealed class NoteEditorUiEvent {
    object InvalidNavigation : NoteEditorUiEvent()
    object NoteNotFound : NoteEditorUiEvent()
}