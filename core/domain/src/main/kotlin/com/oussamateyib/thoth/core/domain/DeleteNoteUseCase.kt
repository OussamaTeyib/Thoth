package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.model.data.Note
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}