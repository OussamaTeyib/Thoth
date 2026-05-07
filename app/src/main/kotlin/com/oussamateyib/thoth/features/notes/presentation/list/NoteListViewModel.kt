package com.oussamateyib.thoth.features.notes.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.usecase.DeleteNoteUseCase
import com.oussamateyib.thoth.features.notes.domain.usecase.GetNotesUseCase
import com.oussamateyib.thoth.features.notes.domain.usecase.InsertNoteUseCase
import com.oussamateyib.thoth.features.notes.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NoteListState())
    val state: State<NoteListState> = _state

    // Holds the active collection job so it can be canceled on re-fetch
    private var getNotesJob: Job? = null

    // Temporarily holds the last deleted note to support undo
    private var recentlyDeletedNote: Note? = null

    init {
        getNotes(state.value.noteOrder)
    }

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) return
                _state.value = state.value.copy(
                    noteOrder = event.noteOrder
                )
                // Reload with the new order
                getNotes(event.noteOrder)
            }

            is NoteListEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is NoteListEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.note)
                }
                recentlyDeletedNote = event.note
            }

            is NoteListEvent.RestoreNote -> {
                // If no note was recently deleted, do nothing
                viewModelScope.launch {
                    insertNoteUseCase(recentlyDeletedNote ?: return@launch)
                }
                recentlyDeletedNote = null
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            getNotesUseCase(noteOrder).collect {
                _state.value = state.value.copy(notes = it)
            }
        }
    }
}