package com.oussamateyib.thoth.features.notes.presentation.editor

import androidx.annotation.StringRes

data class NoteEditorTextFieldState(
    val text: String = "",
    @param:StringRes val hint: Int = 0,
    val isHintVisible: Boolean = true
)