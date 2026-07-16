package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Long) = repository.getNoteById(id)
}
