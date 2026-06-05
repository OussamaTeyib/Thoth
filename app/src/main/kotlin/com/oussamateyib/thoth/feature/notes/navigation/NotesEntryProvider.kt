package com.oussamateyib.thoth.feature.notes.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.feature.notes.presentation.editor.NoteEditorScreen
import com.oussamateyib.thoth.feature.notes.presentation.list.NoteListScreen

// Map the navigation keys of the notes feature to their respective screens
fun EntryProviderScope<NavKey>.notesEntry(
    navigator: Navigator
) {
    entry<NoteListNavKey> {
        NoteListScreen(
            onNoteClick = { noteId ->
                navigator.navigate(NoteEditorNavKey(noteId))
            },
            onAddNote = {
                navigator.navigate(NoteEditorNavKey())
            },
        )
    }
    entry<NoteEditorNavKey> { key ->
        NoteEditorScreen(
            noteId = key.noteId,
            onBackClick = navigator::goBack
        )
    }
}