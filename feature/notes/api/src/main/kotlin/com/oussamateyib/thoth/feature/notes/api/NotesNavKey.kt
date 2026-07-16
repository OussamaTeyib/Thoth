package com.oussamateyib.thoth.feature.notes.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object NoteListNavKey : NavKey

@Serializable
data class NoteEditorNavKey(val noteId: Long = 0L) : NavKey
