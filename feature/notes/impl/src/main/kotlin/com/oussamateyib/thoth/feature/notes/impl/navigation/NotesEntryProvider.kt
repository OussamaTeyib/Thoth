package com.oussamateyib.thoth.feature.notes.impl.navigation

import androidx.compose.material3.DrawerState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.feature.notes.api.NoteEditorNavKey
import com.oussamateyib.thoth.feature.notes.api.NoteListNavKey
import com.oussamateyib.thoth.feature.notes.impl.editor.NoteEditorScreen
import com.oussamateyib.thoth.feature.notes.impl.editor.NoteEditorViewModel
import com.oussamateyib.thoth.feature.notes.impl.list.NoteListScreen
import com.oussamateyib.thoth.feature.notes.impl.list.NoteListViewModel

// Map navigation keys to their corresponding screens
fun EntryProviderScope<NavKey>.notesEntry(
    navigator: Navigator,
    drawerState: DrawerState,
) {
    entry<NoteListNavKey> {
        val viewModel = hiltViewModel<NoteListViewModel>()

        NoteListScreen(
            onNoteClick = { noteId ->
                navigator.navigate(NoteEditorNavKey(noteId))
            },
            onAddNote = {
                navigator.navigate(NoteEditorNavKey())
            },
            viewModel = viewModel,
            drawerState = drawerState,
        )
    }

    entry<NoteEditorNavKey> { key ->
        val viewModel = hiltViewModel<NoteEditorViewModel, NoteEditorViewModel.Factory> { factory ->
            // Pass the note ID to the ViewModel constructor
            factory.create(noteId = key.noteId)
        }

        NoteEditorScreen(
            onBackClick = navigator::goBack,
            viewModel = viewModel,
        )
    }
}
