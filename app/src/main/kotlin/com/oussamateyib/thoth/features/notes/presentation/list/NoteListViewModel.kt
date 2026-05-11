package com.oussamateyib.thoth.features.notes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.features.notes.domain.usecase.DeleteNoteUseCase
import com.oussamateyib.thoth.features.notes.domain.usecase.GetNotesUseCase
import com.oussamateyib.thoth.features.notes.domain.usecase.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(NoteListState())
    val state: StateFlow<NoteListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state
                .map { it.noteOrder }
                .distinctUntilChanged()
                .flatMapLatest { getNotesUseCase(it) }
                .collect { notes ->
                    _state.update {
                        it.copy(notes = notes)
                    }
                }
        }
    }

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) return
                _state.update {
                    it.copy(noteOrder = event.noteOrder)
                }
            }

            NoteListEvent.ToggleOrderSection -> {
                _state.update {
                    it.copy(isOrderSectionVisible = !it.isOrderSectionVisible)
                }
            }

            is NoteListEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.note)
                }
                _state.update {
                    it.copy(recentlyDeletedNote = event.note)
                }
            }

            NoteListEvent.RestoreNote -> {
                val snapshot = _state.value

                // If no note was recently deleted, do nothing
                viewModelScope.launch {
                    insertNoteUseCase(snapshot.recentlyDeletedNote ?: return@launch)
                }
                _state.update {
                    it.copy(recentlyDeletedNote = null)
                }
            }
        }
    }
}