package com.oussamateyib.thoth.features.notes.presentation.util

sealed class NoteScreen(
    val route: String
) {
    object ListScreen : NoteScreen("list_screen")
    object EditorScreen : NoteScreen("editor_screen")
}