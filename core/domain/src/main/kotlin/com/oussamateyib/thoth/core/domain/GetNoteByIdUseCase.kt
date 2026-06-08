package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.model.data.Note
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) : Note? {
        return repository.getNoteById(id)
    }
}