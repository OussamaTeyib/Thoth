package com.oussamateyib.thoth.feature.notes.impl.data.mappers

import com.oussamateyib.thoth.core.database.model.NoteEntity
import com.oussamateyib.thoth.feature.notes.impl.domain.model.Note

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