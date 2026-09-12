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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.core.designsystem.component.TransparentTextField
import com.oussamateyib.thoth.core.ui.NoteColorPicker
import com.oussamateyib.thoth.core.ui.asColor
import com.oussamateyib.thoth.core.ui.util.PaletteLayout
import com.oussamateyib.thoth.feature.notes.impl.R
import kotlinx.coroutines.flow.drop
import com.oussamateyib.thoth.core.designsystem.R as DesignR

@Composable
fun NoteEditorScreen(
    onBackClick: () -> Unit,
    viewModel: NoteEditorViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val noteNotFoundMessage = stringResource(R.string.feature_notes_impl_note_not_found)

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
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteEditorScreen(
    state: NoteEditorState,
    snackbarHostState: SnackbarHostState,
    onEvent: (NoteEditorEvent) -> Unit,
    onBackClick: () -> Unit,
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
            animationSpec = tween(durationMillis = 400),
        )
    }

    val sheetState = rememberBottomSheetState(
        initialValue = SheetValue.Hidden,
        enabledValues = setOf(SheetValue.Hidden, SheetValue.Expanded),
    )

    if (state.isColorPickerVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                onEvent(NoteEditorEvent.ToggleColorPicker)
            },
            containerColor = noteBackgroundAnimatable.value,
            dragHandle = null,
        ) {
            NoteColorPicker(
                selectedColor = state.color,
                onColorChange = {
                    onEvent(NoteEditorEvent.ChangeColor(it))
                },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                layout = PaletteLayout.Row,
            )
        }
    }

    val titleState = rememberTextFieldState(state.title.text)
    val contentState = rememberTextFieldState(state.content.text)

    LaunchedEffect(titleState) {
        snapshotFlow { titleState.text.toString() }
            // Skip seed value to avoid a false update on load
            .drop(1)
            .collect { text ->
                onEvent(NoteEditorEvent.EnteredTitle(text))
            }
    }

    LaunchedEffect(contentState) {
        snapshotFlow { contentState.text.toString() }
            // Skip seed value to avoid a false update on load
            .drop(1)
            .collect { text ->
                onEvent(NoteEditorEvent.EnteredContent(text))
            }
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
                    containerColor = Color.Transparent,
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        },
                    ) {
                        Icon(
                            painter = painterResource(DesignR.drawable.core_designsystem_ic_arrow_back),
                            contentDescription = stringResource(DesignR.string.core_designsystem_back),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(NoteEditorEvent.ToggleColorPicker)
                        },
                    ) {
                        Icon(
                            painter = painterResource(DesignR.drawable.core_designsystem_ic_palette),
                            contentDescription = stringResource(R.string.feature_notes_impl_change_color),
                        )
                    }
                },
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
                .verticalScroll(verticalScroll),
        ) {
            TransparentTextField(
                state = titleState,
                hint = stringResource(state.title.hint),
                isHintVisible = titleState.text.isEmpty(),
                textStyle = MaterialTheme.typography.headlineMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                onKeyboardAction = {
                    // Move focus to the content field and place the cursor at the end
                    contentFocusRequester.requestFocus()
                    contentState.edit {
                        selection = TextRange(length)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            TransparentTextField(
                state = contentState,
                hint = stringResource(state.content.hint),
                isHintVisible = contentState.text.isEmpty(),
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(contentFocusRequester),
            )
        }
    }
}
