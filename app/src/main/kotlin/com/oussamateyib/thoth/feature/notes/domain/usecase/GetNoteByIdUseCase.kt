package com.oussamateyib.thoth.feature.notes.domain.usecase

import com.oussamateyib.thoth.feature.notes.domain.model.Note
import com.oussamateyib.thoth.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id : Int) : Note? {
        return repository.getNoteById(id)
    }
}