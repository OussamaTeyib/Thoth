package com.oussamateyib.thoth.feature.notes.impl.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.DeleteNoteUseCase
import com.oussamateyib.thoth.core.domain.GetNotesStreamUseCase
import com.oussamateyib.thoth.core.domain.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                _state.update {
                    it.copy(
                        noteOrder = event.noteOrder,
                        isSortSheetVisible = false
                    )
                }
            }

            NoteListEvent.ToggleSortSheet -> {
                _state.update {
                    it.copy(isSortSheetVisible = !it.isSortSheetVisible)
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
                val notesToDelete = state.value.notes
                    .filter { it.id in state.value.selectedNoteIds }

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
                val notesToRestore = state.value.recentlyDeletedNotes
                viewModelScope.launch {
                    notesToRestore.forEach {
                        insertNoteUseCase(it)
                    }
                }
                _state.update {
                    it.copy(recentlyDeletedNotes = emptyList())
                }
            }

            NoteListEvent.ToggleColorPicker -> {
                _state.update {
                    it.copy(isColorPickerVisible = !it.isColorPickerVisible)
                }
            }

            is NoteListEvent.ChangeColor -> {
                val notesToUpdate = state.value.notes
                    .filter { it.id in state.value.selectedNoteIds }
                    .map { it.copy(color = event.color) }

                viewModelScope.launch {
                    notesToUpdate.forEach {
                        insertNoteUseCase(it)
                    }
                }
                _state.update {
                    it.copy(
                        isColorPickerVisible = false,
                        selectedNoteIds = emptySet()
                    )
                }
            }
        }
    }
}