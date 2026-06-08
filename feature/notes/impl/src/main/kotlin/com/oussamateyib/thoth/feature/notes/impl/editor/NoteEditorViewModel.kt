package com.oussamateyib.thoth.feature.notes.impl.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.feature.notes.impl.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.domain.GetNoteByIdUseCase
import com.oussamateyib.thoth.core.domain.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = NoteEditorViewModel.Factory::class)
class NoteEditorViewModel @AssistedInject constructor(
    @Assisted private val noteId: Int,
    getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(noteId: Int): NoteEditorViewModel
    }

    private val _state = MutableStateFlow(NoteEditorState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<NoteEditorUiEvent>()
    val events = _events.asSharedFlow()

    private var saveJob: Job? = null

    init {
        when (noteId) {
            -1 -> _state.update {
                it.copy(
                    isLoading = false
                )
            }

            else -> viewModelScope.launch {
                when (val note = getNoteByIdUseCase(noteId)) {
                    null -> _events.emit(NoteEditorUiEvent.NoteNotFound)

                    else -> _state.update {
                        it.copy(
                            title = NoteEditorTextFieldState(
                                text = note.title,
                                hint = R.string.note_title_hint,
                                isHintVisible = note.title.isEmpty()
                            ),
                            content = NoteEditorTextFieldState(
                                text = note.content,
                                hint = R.string.note_content_hint,
                                isHintVisible = note.content.isEmpty()
                            ),
                            color = note.color,
                            id = noteId,
                            isLoading = false
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
                saveNote()
            }

            is NoteEditorEvent.ChangeTitleFocus -> {
                _state.update {
                    it.copy(
                        title = it.title.copy(
                            isHintVisible = !event.focusState.isFocused && it.title.text.isEmpty()
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
                saveNote()
            }

            is NoteEditorEvent.ChangeContentFocus -> {
                _state.update {
                    it.copy(
                        content = it.content.copy(
                            isHintVisible = !event.focusState.isFocused && it.content.text.isEmpty()
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
                saveNote()
            }

            NoteEditorEvent.ToggleColorPicker -> {
                _state.update {
                    it.copy(
                        isColorPickerVisible = !it.isColorPickerVisible
                    )
                }
            }
        }
    }

    private fun saveNote() {
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            delay(300)
            val snapshot = _state.value
            val newId = insertNoteUseCase(
                Note(
                    title = snapshot.title.text,
                    content = snapshot.content.text,
                    timestamp = System.currentTimeMillis(),
                    color = snapshot.color,
                    id = snapshot.id
                )
            )
            // Update state if this was a new note
            if (snapshot.id == null) {
                _state.update {
                    it.copy(
                        id = newId.toInt()
                    )
                }
            }
        }
    }
}