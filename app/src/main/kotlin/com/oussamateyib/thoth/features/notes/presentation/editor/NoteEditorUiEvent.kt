package com.oussamateyib.thoth.features.notes.presentation.editor

sealed class NoteEditorUiEvent {
    data object NoteSaved : NoteEditorUiEvent()
}