package com.oussamateyib.thoth.core.model.data

data class Note(
    val id: Long = 0L,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: NoteColor
)