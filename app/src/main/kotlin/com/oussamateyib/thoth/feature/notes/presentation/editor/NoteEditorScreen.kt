package com.oussamateyib.thoth.feature.notes.presentation.editor

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.R
import com.oussamateyib.thoth.feature.notes.presentation.editor.components.ColorPicker
import com.oussamateyib.thoth.feature.notes.presentation.editor.components.TransparentHintTextField

@Composable
fun NoteEditorScreen(
    onBackClick: () -> Unit,
    viewModel: NoteEditorViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val noteNotFoundMessage = stringResource(R.string.note_not_found)

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                NoteEditorUiEvent.NoteNotFound -> {
                    snackbarHostState.showSnackbar(noteNotFoundMessage)
                    onBackClick()
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteEditorScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = { event -> viewModel.onEvent(event) },
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteEditorScreen(
    state: NoteEditorState,
    snackbarHostState: SnackbarHostState,
    onEvent: (NoteEditorEvent) -> Unit,
    onBackClick: () -> Unit
) {
    if (state.isLoading) return

    val noteBackgroundAnimatable = remember {
        Animatable(Color(state.color))
    }

    LaunchedEffect(state.color) {
        noteBackgroundAnimatable.animateTo(
            targetValue = Color(state.color),
            animationSpec = tween(durationMillis = 400)
        )
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if (state.isColorPickerVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onEvent(NoteEditorEvent.ToggleColorPicker)
            }
        ) {
            ColorPicker(
                selectedColor = state.color,
                onColorChange = {
                    onEvent(NoteEditorEvent.ChangeColor(it))
                },
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(NoteEditorEvent.ToggleColorPicker)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.dropper_eye),
                            contentDescription = stringResource(R.string.pick_color)
                        )
                    }
                }
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            TransparentHintTextField(
                text = state.title.text,
                hint = stringResource(state.title.hint),
                isHintVisible = state.title.isHintVisible,
                onValueChange = {
                    onEvent(NoteEditorEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    onEvent(NoteEditorEvent.ChangeTitleFocus(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            TransparentHintTextField(
                text = state.content.text,
                hint = stringResource(state.content.hint),
                isHintVisible = state.content.isHintVisible,
                onValueChange = {
                    onEvent(NoteEditorEvent.EnteredContent(it))
                },
                onFocusChange = {
                    onEvent(NoteEditorEvent.ChangeContentFocus(it))
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}