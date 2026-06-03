package com.oussamateyib.thoth.feature.notes.presentation.editor

sealed class NoteEditorUiEvent {
    object InvalidNavigation : NoteEditorUiEvent()
    object NoteNotFound : NoteEditorUiEvent()
}