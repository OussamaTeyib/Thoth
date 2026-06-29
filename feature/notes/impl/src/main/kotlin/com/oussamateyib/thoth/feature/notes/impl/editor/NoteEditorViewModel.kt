package com.oussamateyib.thoth.feature.notes.impl.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.GetNoteByIdUseCase
import com.oussamateyib.thoth.core.domain.InsertNoteUseCase
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.feature.notes.impl.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel(assistedFactory = NoteEditorViewModel.Factory::class)
class NoteEditorViewModel @AssistedInject constructor(
    @Assisted private val noteId: Long,
    getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(noteId: Long): NoteEditorViewModel
    }

    private val _state = MutableStateFlow(NoteEditorState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<NoteEditorUiEvent>()
    val events = _events.asSharedFlow()

    private var saveJob: Job? = null

    init {
        when (noteId) {
            0L -> _state.update {
                it.copy(isLoading = false)
            }

            else -> viewModelScope.launch {
                when (val note = getNoteByIdUseCase(noteId)) {
                    null -> _events.emit(NoteEditorUiEvent.NoteNotFound)

                    else -> _state.update {
                        it.copy(
                            id = noteId,
                            createdAt = note.createdAt,
                            title = NoteEditorTextFieldState(
                                text = note.title,
                                hint = R.string.note_title_hint
                            ),
                            content = NoteEditorTextFieldState(
                                text = note.content,
                                hint = R.string.note_content_hint
                            ),
                            color = note.color,
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

            is NoteEditorEvent.ChangeColor -> {
                _state.update {
                    it.copy(color = event.color)
                }
                saveNote()
            }

            NoteEditorEvent.ToggleColorPicker -> {
                _state.update {
                    it.copy(isColorPickerVisible = !it.isColorPickerVisible)
                }
            }
        }
    }

    private fun saveNote() {
        // Cancel any existing save job
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            // Delay to avoid saving too frequently
            delay(300.milliseconds)

            with(_state.value) {
                val now = Clock.System.now()
                val newId = insertNoteUseCase(
                    Note(
                        id = id,
                        title = title.text,
                        content = content.text,
                        createdAt = createdAt ?: now,
                        updatedAt = now,
                        color = color
                    )
                )

                // Update the state if this was a newly created note
                if (id == 0L) {
                    _state.update {
                        it.copy(
                            id = newId,
                            createdAt = now
                        )
                    }
                }
            }
        }
    }
}