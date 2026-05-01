package com.oussamateyib.thoth.features.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oussamateyib.thoth.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedPink, RedOrange, LightGreen, Violet, BabyBlue)
    }
}
