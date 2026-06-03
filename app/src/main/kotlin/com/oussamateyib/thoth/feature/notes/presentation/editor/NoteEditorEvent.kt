package com.oussamateyib.thoth.feature.notes.presentation.editor

import androidx.compose.ui.focus.FocusState

sealed class NoteEditorEvent {
    data class EnteredTitle(val title: String) : NoteEditorEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : NoteEditorEvent()
    data class EnteredContent(val content: String) : NoteEditorEvent()
    data class ChangeContentFocus(val focusState: FocusState) : NoteEditorEvent()
    data class ChangeColor(val color: Int) : NoteEditorEvent()
    object ToggleColorPicker : NoteEditorEvent()
}