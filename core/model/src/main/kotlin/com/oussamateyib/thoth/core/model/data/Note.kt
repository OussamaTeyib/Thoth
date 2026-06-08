package com.oussamateyib.thoth.core.model.data

data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
)