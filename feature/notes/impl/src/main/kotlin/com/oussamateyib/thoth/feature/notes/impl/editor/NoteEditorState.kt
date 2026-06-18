package com.oussamateyib.thoth.feature.notes.impl.editor

import androidx.compose.ui.graphics.toArgb
import com.oussamateyib.thoth.core.designsystem.theme.NoteColors
import com.oussamateyib.thoth.feature.notes.impl.R

data class NoteEditorState(
    val title: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_title_hint),
    val content: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_content_hint),
    val color: Int = NoteColors.noteColors.random().toArgb(),
    val isColorPickerVisible: Boolean = false,
    val id: Int? = null,
    val isLoading: Boolean = true
)