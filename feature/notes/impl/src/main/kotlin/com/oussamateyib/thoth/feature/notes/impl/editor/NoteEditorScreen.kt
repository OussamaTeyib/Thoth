package com.oussamateyib.thoth.feature.notes.impl.editor

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.core.designsystem.components.TransparentTextField
import com.oussamateyib.thoth.core.ui.NoteColorPicker
import com.oussamateyib.thoth.core.ui.asColor
import com.oussamateyib.thoth.core.ui.util.PaletteLayout
import com.oussamateyib.thoth.feature.notes.impl.R

@Composable
fun NoteEditorScreen(
    onBackClick: () -> Unit,
    viewModel: NoteEditorViewModel
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

    val noteColor = state.color.asColor()

    val noteBackgroundAnimatable = remember {
        Animatable(noteColor)
    }

    // Animate the background color when the note color changes
    LaunchedEffect(state.color) {
        noteBackgroundAnimatable.animateTo(
            targetValue = noteColor,
            animationSpec = tween(durationMillis = 400)
        )
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (state.isColorPickerVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onEvent(NoteEditorEvent.ToggleColorPicker)
            },
            containerColor = noteBackgroundAnimatable.value
        ) {
            NoteColorPicker(
                selectedColor = state.color,
                onColorChange = {
                    onEvent(NoteEditorEvent.ChangeColor(it))
                },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                layout = PaletteLayout.Row
            )
        }
    }

    var titleFieldValue by remember {
        mutableStateOf(TextFieldValue(text = state.title.text))
    }
    var contentFieldValue by remember {
        mutableStateOf(TextFieldValue(text = state.content.text))
    }

    val contentFocusRequester = remember { FocusRequester() }

    // Autofocus the content field when opening a new note
    LaunchedEffect(Unit) {
        if (state.id == 0L) {
            contentFocusRequester.requestFocus()
        }
    }

    val verticalScroll = rememberScrollState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
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
                            painter = painterResource(R.drawable.palette),
                            contentDescription = stringResource(R.string.change_color)
                        )
                    }
                }
            )
        },
        containerColor = noteBackgroundAnimatable.value,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .verticalScroll(verticalScroll)
        ) {
            TransparentTextField(
                value = titleFieldValue,
                hint = stringResource(state.title.hint),
                isHintVisible = state.title.text.isEmpty(),
                onValueChange = {
                    titleFieldValue = it
                    onEvent(NoteEditorEvent.EnteredTitle(it.text))
                },
                textStyle = MaterialTheme.typography.headlineMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // Move focus to the content field and place the cursor at the end
                        contentFocusRequester.requestFocus()
                        contentFieldValue = contentFieldValue.copy(
                            selection = TextRange(contentFieldValue.text.length)
                        )
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            TransparentTextField(
                value = contentFieldValue,
                hint = stringResource(state.content.hint),
                isHintVisible = state.content.text.isEmpty(),
                onValueChange = {
                    contentFieldValue = it
                    onEvent(NoteEditorEvent.EnteredContent(it.text))
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(contentFocusRequester)
            )
        }
    }
}