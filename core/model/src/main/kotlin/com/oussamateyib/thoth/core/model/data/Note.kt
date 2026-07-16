package com.oussamateyib.thoth.core.model.data

import kotlin.time.Instant

data class Note(
    val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val color: NoteColor,
)
