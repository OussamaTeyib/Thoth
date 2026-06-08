package com.oussamateyib.thoth.feature.notes.impl.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.feature.notes.impl.list.components.NoteItem
import com.oussamateyib.thoth.feature.notes.impl.list.components.OrderSection
import com.oussamateyib.thoth.feature.notes.impl.R
import kotlinx.coroutines.launch

@Composable
fun NoteListScreen(
    onNoteClick: (Int) -> Unit,
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
    onNoteClick: (Int) -> Unit,
    onAddNote: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Connect the TopAppBar scroll behavior to the Scaffold
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val noteDeletedMessage = stringResource(R.string.note_deleted)
    val undoLabel = stringResource(R.string.undo)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.list_screen_top_bar_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(NoteListEvent.ToggleOrderSection)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.sort),
                            contentDescription = stringResource(R.string.sort_notes)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddNote()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = stringResource(R.string.add_note)
                )
            }
        },
        containerColor = Color.Transparent,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn(tween(200)) + expandVertically(),
                exit = fadeOut(tween(200)) + shrinkVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        onEvent(NoteListEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    state.notes,
                    key = { it.id!! }
                ) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNoteClick(note.id!!)
                            },
                        onDeleteClick = {
                            onEvent(NoteListEvent.DeleteNote(note))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = noteDeletedMessage,
                                    actionLabel = undoLabel,
                                    duration = SnackbarDuration.Short
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    onEvent(NoteListEvent.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}