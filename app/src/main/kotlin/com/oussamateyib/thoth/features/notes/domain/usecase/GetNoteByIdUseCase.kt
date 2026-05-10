package com.oussamateyib.thoth.features.notes.domain.usecase

import com.oussamateyib.thoth.features.notes.domain.model.Note
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import jakarta.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id : Int) : Note? {
        return repository.getNoteById(id)
    }
}