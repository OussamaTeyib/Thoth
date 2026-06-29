package com.oussamateyib.thoth.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.model.data.NoteColor
import kotlin.time.Instant

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val color: NoteColor
)

fun NoteEntity.asExternalModel(): Note = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt,
    color = color
)