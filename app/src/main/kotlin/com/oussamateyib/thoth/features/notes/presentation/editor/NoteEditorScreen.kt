package com.oussamateyib.thoth.features.notes.presentation.editor

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.oussamateyib.thoth.R
import com.oussamateyib.thoth.features.notes.presentation.editor.components.TransparentHintTextField
import com.oussamateyib.thoth.features.notes.presentation.editor.util.NoteColors
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NoteEditorScreen(
    navController: NavController,
    viewModel: NoteEditorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val noteBackgroundAnimatable = remember {
        Animatable(Color(state.color))
    }

    LaunchedEffect(state.color) {
        noteBackgroundAnimatable.animateTo(
            targetValue = Color(state.color),
            animationSpec = tween(durationMillis = 400)
        )
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                NoteEditorUiEvent.NoteSaved -> {
                    navController.navigateUp()
                }
            }
        }
    }

    BackHandler {
        viewModel.onEvent(NoteEditorEvent.NavigateBack)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteEditorEvent.SaveNote)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_save),
                    contentDescription = stringResource(R.string.save_note)
                )
            }
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoteColors.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    val isSelected = state.color == colorInt

                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 46.dp else 40.dp)
                            .shadow(if (isSelected) 8.dp else 4.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (isSelected) 3.dp else 0.dp,
                                color = if (isSelected) Color.White else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                viewModel.onEvent(NoteEditorEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(20.dp))
            TransparentHintTextField(
                text = state.title.text,
                hint = stringResource(state.title.hint),
                isHintVisible = state.title.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(NoteEditorEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteEditorEvent.ChangeTitleFocus(it))
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
                    viewModel.onEvent(NoteEditorEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteEditorEvent.ChangeContentFocus(it))
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}