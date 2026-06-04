package com.oussamateyib.thoth.feature.notes.data.mappers

import com.oussamateyib.thoth.feature.notes.data.local.NoteEntity
import com.oussamateyib.thoth.feature.notes.domain.model.Note

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