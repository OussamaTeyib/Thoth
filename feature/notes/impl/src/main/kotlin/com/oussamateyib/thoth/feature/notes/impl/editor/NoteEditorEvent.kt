package com.oussamateyib.thoth.feature.notes.impl.editor

import com.oussamateyib.thoth.core.model.data.NoteColor

sealed class NoteEditorEvent {
    data class EnteredTitle(val title: String) : NoteEditorEvent()
    data class EnteredContent(val content: String) : NoteEditorEvent()
    data class ChangeColor(val color: NoteColor) : NoteEditorEvent()
    object ToggleColorPicker : NoteEditorEvent()
}