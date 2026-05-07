package com.oussamateyib.thoth.features.notes.presentation.editor

import androidx.compose.ui.graphics.toArgb
import com.oussamateyib.thoth.features.notes.presentation.editor.util.NoteColors

data class NoteEditorState(
    val title: NoteEditorTextFieldState = NoteEditorTextFieldState(),
    val content: NoteEditorTextFieldState =  NoteEditorTextFieldState(),
    val color: Int = NoteColors.noteColors.random().toArgb()
)