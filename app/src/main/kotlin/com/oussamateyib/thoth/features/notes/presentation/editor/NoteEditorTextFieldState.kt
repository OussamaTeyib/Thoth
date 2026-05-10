package com.oussamateyib.thoth.features.notes.presentation.editor

data class NoteEditorTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)