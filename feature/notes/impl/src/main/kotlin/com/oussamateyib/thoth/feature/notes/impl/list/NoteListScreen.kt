package com.oussamateyib.thoth.feature.notes.impl.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.core.ui.NoteColorPicker
import com.oussamateyib.thoth.core.ui.NoteSortSheet
import com.oussamateyib.thoth.core.ui.noteItems
import com.oussamateyib.thoth.core.ui.util.toLocalizedFormat
import com.oussamateyib.thoth.feature.notes.impl.R
import kotlinx.coroutines.launch

@Composable
fun NoteListScreen(
    onNoteClick: (Long) -> Unit,
    onAddNote: () -> Unit,
    viewModel: NoteListViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteListScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = { event -> viewModel.onEvent(event) },
        onNoteClick = onNoteClick,
        onAddNote = onAddNote
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteListScreen(
    state: NoteListState,
    snackbarHostState: SnackbarHostState,
    onEvent: (NoteListEvent) -> Unit,
    onNoteClick: (Long) -> Unit,
    onAddNote: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Clear selection when back button is pressed in selection mode
    BackHandler(enabled = state.isSelectionMode) {
        onEvent(NoteListEvent.ClearSelection)
    }

    if (state.isColorPickerVisible) {
        BasicAlertDialog(
            onDismissRequest = { onEvent(NoteListEvent.ToggleColorPicker) }
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                modifier = Modifier.width(280.dp)
            ) {
                NoteColorPicker(
                    selectedColor = state.commonSelectedColor,
                    onColorChange = {
                        onEvent(NoteListEvent.ChangeColor(it))
                    },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (state.isSortSheetVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onEvent(NoteListEvent.ToggleSortSheet)
            }
        ) {
            NoteSortSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                noteOrder = state.noteOrder,
                onOrderChange = {
                    onEvent(NoteListEvent.Order(it))
                }
            )
        }
    }

    val scrollBehavior = if (state.isSelectionMode) {
        TopAppBarDefaults.pinnedScrollBehavior()
    } else {
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    }

    val selectedNotesDeletedMessage = stringResource(R.string.feature_notes_impl_selected_notes_deleted)
    val undoLabel = stringResource(R.string.feature_notes_impl_undo)

    Scaffold(
        modifier = Modifier
            // Connect scroll events from the content to the top bar behavior
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (state.isSelectionMode) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    },
                    scrolledContainerColor = if (state.isSelectionMode) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                navigationIcon = {
                    if (state.isSelectionMode) {
                        IconButton(
                            onClick = { onEvent(NoteListEvent.ClearSelection) }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.feature_notes_impl_close),
                                contentDescription = stringResource(R.string.feature_notes_impl_clear_selection)
                            )
                        }
                    }
                },
                title = {
                    Text(
                        // If in selection mode, show the number of selected notes
                        text = if (state.isSelectionMode) {
                            state.selectedNoteIds.size.toLocalizedFormat()
                        } else {
                            stringResource(R.string.feature_notes_impl_list_screen_top_bar_title)
                        },
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    if (state.isSelectionMode) {
                        IconButton(
                            onClick = {
                                onEvent(NoteListEvent.ToggleColorPicker)
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.feature_notes_impl_palette),
                                contentDescription = stringResource(R.string.feature_notes_impl_change_color)
                            )
                        }
                        IconButton(
                            onClick = {
                                onEvent(NoteListEvent.DeleteSelectedNotes)
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = selectedNotesDeletedMessage,
                                        actionLabel = undoLabel,
                                        duration = SnackbarDuration.Short
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        onEvent(NoteListEvent.RestoreDeletedNotes)
                                    }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.feature_notes_impl_delete),
                                contentDescription = stringResource(R.string.feature_notes_impl_delete_selected_notes)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                onEvent(NoteListEvent.ToggleSortSheet)
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.feature_notes_impl_sort),
                                contentDescription = stringResource(R.string.feature_notes_impl_sort_notes)
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (state.isSelectionMode) {
                        onEvent(NoteListEvent.ClearSelection)
                    }
                    onAddNote()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.feature_notes_impl_add),
                    contentDescription = stringResource(R.string.feature_notes_impl_add_note)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            noteItems(
                items = state.notes,
                selectedItemIds = state.selectedNoteIds,
                onItemClick = {
                    if (state.isSelectionMode) {
                        onEvent(NoteListEvent.SelectNote(it))
                    } else {
                        onNoteClick(it)
                    }
                },
                onItemLongClick = {
                    onEvent(NoteListEvent.SelectNote(it))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}