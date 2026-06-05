package com.oussamateyib.thoth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.oussamateyib.thoth.feature.notes.api.NoteListRoute
import com.oussamateyib.thoth.feature.notes.impl.navigation.navigateToNoteEditor
import com.oussamateyib.thoth.feature.notes.impl.navigation.notesScreen
import com.oussamateyib.thoth.ui.ThothAppState

@Composable
fun ThothNavHost(
    appState: ThothAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = NoteListRoute,
        modifier = modifier
    ) {
        notesScreen(
            onNoteClick = { noteId -> navController.navigateToNoteEditor(noteId) },
            onAddNote = navController::navigateToNoteEditor,
            onBackClick = navController::popBackStack,
        )
    }
}