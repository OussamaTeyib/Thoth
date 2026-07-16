package com.oussamateyib.thoth.feature.notes.impl.editor

import com.oussamateyib.thoth.core.model.data.NoteColor
import com.oussamateyib.thoth.feature.notes.impl.R
import kotlin.time.Instant

data class NoteEditorState(
    val id: Long = 0L,
    val createdAt: Instant? = null,
    val title: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.feature_notes_impl_note_title_hint),
    val content: NoteEditorTextFieldState = NoteEditorTextFieldState(hint = R.string.feature_notes_impl_note_content_hint),
    val color: NoteColor = NoteColor.Default,
    val isColorPickerVisible: Boolean = false,
    val isLoading: Boolean = true,
)
