package com.oussamateyib.thoth.feature.notes.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oussamateyib.thoth.feature.notes.api.NoteEditorRoute
import com.oussamateyib.thoth.feature.notes.api.NoteListRoute
import com.oussamateyib.thoth.feature.notes.impl.presentation.editor.NoteEditorScreen
import com.oussamateyib.thoth.feature.notes.impl.presentation.list.NoteListScreen

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