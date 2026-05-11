package com.oussamateyib.thoth.features.notes.presentation.util

sealed class Screen(
    val route: String
) {
    object ListScreen : Screen("list_screen")
    object EditorScreen : Screen("editor_screen")
}