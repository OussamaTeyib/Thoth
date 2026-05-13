package com.oussamateyib.thoth.features.notes.domain.usecase

import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import jakarta.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note : Note): Long {
        return repository.insertNote(note)
    }
}