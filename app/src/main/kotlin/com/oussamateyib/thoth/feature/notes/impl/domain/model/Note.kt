package com.oussamateyib.thoth.feature.notes.impl.domain.model

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null
)