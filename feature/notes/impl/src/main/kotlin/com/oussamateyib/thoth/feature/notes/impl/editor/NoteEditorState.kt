package com.oussamateyib.thoth.feature.notes.impl.editor

import com.oussamateyib.thoth.core.model.data.NoteColor
import com.oussamateyib.thoth.feature.notes.impl.R

data class NoteEditorState(
    val id: Long = 0L,
    val title: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_title_hint),
    val content: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.note_content_hint),
    val color: NoteColor = NoteColor.Default,
    val isColorPickerVisible: Boolean = false,
    val isLoading: Boolean = true
)