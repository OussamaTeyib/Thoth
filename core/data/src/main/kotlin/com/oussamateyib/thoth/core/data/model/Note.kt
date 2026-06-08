package com.oussamateyib.thoth.core.data.model

import com.oussamateyib.thoth.core.database.model.NoteEntity
import com.oussamateyib.thoth.core.model.data.Note

internal fun Note.asEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    color = color
)