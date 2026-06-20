package com.oussamateyib.thoth.feature.notes.impl.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.DeleteNoteUseCase
import com.oussamateyib.thoth.core.domain.GetNotesStreamUseCase
import com.oussamateyib.thoth.core.domain.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NoteListViewModel @Inject constructor(
    getNotesStreamUseCase: GetNotesStreamUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state
                .map { it.noteOrder }
                .distinctUntilChanged()
                .flatMapLatest { getNotesStreamUseCase(it) }
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

            is NoteListEvent.SelectNote -> {
                _state.update {
                    val updatedIds = if (event.id in it.selectedNoteIds) {
                        it.selectedNoteIds - event.id
                    } else {
                        it.selectedNoteIds + event.id
                    }
                    it.copy(selectedNoteIds = updatedIds)
                }
            }

            NoteListEvent.ClearSelection -> {
                _state.update {
                    it.copy(selectedNoteIds = emptySet())
                }
            }

            NoteListEvent.DeleteSelectedNotes -> {
                val notesToDelete =
                    state.value.notes.filter { it.id!! in state.value.selectedNoteIds }
                viewModelScope.launch {
                    notesToDelete.forEach {
                        deleteNoteUseCase(it)
                    }
                }
                _state.update {
                    it.copy(
                        selectedNoteIds = emptySet(),
                        recentlyDeletedNotes = notesToDelete
                    )
                }
            }

            NoteListEvent.RestoreDeletedNotes -> {
                val snapshot = state.value
                viewModelScope.launch {
                    snapshot.recentlyDeletedNotes.forEach {
                        insertNoteUseCase(it)
                    }
                }
                _state.update {
                    it.copy(recentlyDeletedNotes = emptyList())
                }
            }
        }
    }
}