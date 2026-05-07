package com.oussamateyib.thoth.features.notes.presentation.editor

sealed class NoteEditorUiEvent {
    data class ShowSnackbar(val message : String) : NoteEditorUiEvent()
    data object NoteSaved : NoteEditorUiEvent()
}