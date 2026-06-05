package com.oussamateyib.thoth.feature.notes.impl.presentation.editor

import androidx.compose.ui.graphics.toArgb
import com.oussamateyib.thoth.R
import com.oussamateyib.thoth.feature.notes.impl.presentation.editor.util.NoteColors

data class NoteEditorState(
    val title: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_title_hint),
    val content: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_content_hint),
    val color: Int = NoteColors.noteColors.random().toArgb(),
    val isColorPickerVisible: Boolean = false,
    val id: Int? = null,
    val isLoading: Boolean = true
)