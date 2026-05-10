package com.oussamateyib.thoth.features.notes.presentation.editor

data class NoteEditorTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisibile: Boolean = true
)