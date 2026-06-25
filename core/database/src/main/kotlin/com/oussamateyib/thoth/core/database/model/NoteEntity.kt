package com.oussamateyib.thoth.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.model.data.NoteColor

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: NoteColor
)

fun NoteEntity.asExternalModel(): Note = Note(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    color = color
)