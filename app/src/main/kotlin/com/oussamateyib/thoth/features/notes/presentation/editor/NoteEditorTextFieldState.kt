package com.oussamateyib.thoth.features.notes.presentation.editor

import com.oussamateyib.thoth.features.notes.domain.model.Note

class NoteEditorTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisibile: Boolean = true
)