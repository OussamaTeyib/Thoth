package com.oussamateyib.thoth.features.notes.presentation.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.usecase.GetNoteByIdUseCase
import com.oussamateyib.thoth.features.notes.domain.usecase.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(NoteEditorState())
    val state: StateFlow<NoteEditorState> = _state.asStateFlow()

    // Set up a one-way pipe from the ViewModel to the UI
    private val _uiEvents = Channel<NoteEditorUiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        savedStateHandle.get<Int>("noteId")
            ?.takeIf { it != -1 }
            ?.let { noteId ->
                viewModelScope.launch {
                    getNoteByIdUseCase(noteId)?.let { note ->
                        _state.update {
                            it.copy(
                                title = NoteEditorTextFieldState(
                                    text = note.title,
                                    isHintVisible = false
                                ),
                                content = NoteEditorTextFieldState(
                                    text = note.content,
                                    isHintVisible = false
                                ),
                                color = note.color,
                                id = noteId
                            )
                        }
                    }
                }
            }
    }

    fun onEvent(event: NoteEditorEvent) {
        when (event) {
            is NoteEditorEvent.EnteredTitle -> {
                _state.update {
                    it.copy(
                        title = it.title.copy(
                            text = event.title
                        )
                    )
                }
            }

            is NoteEditorEvent.ChangeTitleFocus -> {
                _state.update {
                    it.copy(
                        title = it.title.copy(
                            isHintVisible = !event.focusState.isFocused
                        )
                    )
                }
            }

            is NoteEditorEvent.EnteredContent -> {
                _state.update {
                    it.copy(
                        content = it.content.copy(
                            text = event.content
                        )
                    )
                }
            }

            is NoteEditorEvent.ChangeContentFocus -> {
                _state.update {
                    it.copy(
                        content = it.content.copy(
                            isHintVisible = !event.focusState.isFocused
                        )
                    )
                }
            }

            is NoteEditorEvent.ChangeColor -> {
                _state.update {
                    it.copy(
                        color = event.color
                    )
                }
            }

            NoteEditorEvent.SaveNote -> {
                val snapshot = _state.value
                viewModelScope.launch {
                    insertNoteUseCase(
                        Note(
                            title = snapshot.title.text,
                            content = snapshot.content.text,
                            timestamp = System.currentTimeMillis(),
                            color = snapshot.color,
                            id = snapshot.id
                        )
                    )
                    _uiEvents.send(NoteEditorUiEvent.NoteSaved)
                }
            }
        }
    }
}