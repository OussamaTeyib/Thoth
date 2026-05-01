package com.oussamateyib.thoth.features.notes.data.mappers

import com.oussamateyib.thoth.features.notes.data.local.NoteEntity
import com.oussamateyib.thoth.features.notes.domain.model.Note

fun NoteEntity.toDomain(): Note = Note(
    title = title,
    content = content,
    timestamp = timestamp,
    color = color,
    id = id
)

fun Note.toEntity(): NoteEntity = NoteEntity(
    title = title,
    content = content,
    timestamp = timestamp,
    color = color,
    id = id
)