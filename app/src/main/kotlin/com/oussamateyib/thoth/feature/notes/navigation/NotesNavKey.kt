package com.oussamateyib.thoth.feature.notes.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object NoteListNavKey : NavKey

@Serializable
data class NoteEditorNavKey(val noteId: Int = -1) : NavKey