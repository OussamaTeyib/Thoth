package com.oussamateyib.thoth.feature.notes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oussamateyib.thoth.feature.notes.presentation.editor.NoteEditorScreen
import com.oussamateyib.thoth.feature.notes.presentation.list.NoteListScreen
import kotlinx.serialization.Serializable

@Serializable
data object NoteListRoute

@Serializable
data class NoteEditorRoute(val noteId: Int = -1)

fun NavController.navigateToNoteEditor(
    noteId: Int = NoteEditorRoute().noteId
) = navigate(route = NoteEditorRoute(noteId))

fun NavGraphBuilder.notesScreen(
    onNoteClick: (Int) -> Unit,
    onAddNote: () -> Unit,
    onBackClick: () -> Unit
) {
    composable<NoteListRoute> {
        NoteListScreen(
            onNoteClick = onNoteClick,
            onAddNote = onAddNote,
        )
    }
    composable<NoteEditorRoute> {
        NoteEditorScreen(onBackClick = onBackClick)
    }
}