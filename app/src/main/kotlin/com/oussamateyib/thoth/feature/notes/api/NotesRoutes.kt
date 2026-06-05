package com.oussamateyib.thoth.feature.notes.api

import kotlinx.serialization.Serializable

@Serializable
data object NoteListRoute

@Serializable
data class NoteEditorRoute(val noteId: Int = -1)