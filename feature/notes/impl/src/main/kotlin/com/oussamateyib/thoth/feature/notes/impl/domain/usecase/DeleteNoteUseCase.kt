package com.oussamateyib.thoth.feature.notes.impl.domain.usecase

import com.oussamateyib.thoth.feature.notes.impl.domain.model.Note
import com.oussamateyib.thoth.feature.notes.impl.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note : Note) {
        repository.deleteNote(note)
    }
}