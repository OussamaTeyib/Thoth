package com.oussamateyib.thoth.features.notes.domain.usecase

import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    val repository: NoteRepository
) {
    suspend operator fun invoke(note : Note) {
        repository.deleteNote(note)
    }
}