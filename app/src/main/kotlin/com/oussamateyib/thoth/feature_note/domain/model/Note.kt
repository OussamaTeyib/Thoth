package com.oussamateyib.thoth.feature_note.domain.model

import androidx.room.Entity
import com.oussamateyib.thoth.ui.theme.BabyBlue
import com.oussamateyib.thoth.ui.theme.LightGreen
import com.oussamateyib.thoth.ui.theme.RedOrange
import com.oussamateyib.thoth.ui.theme.RedPink
import com.oussamateyib.thoth.ui.theme.Violet

@Entity
data class Note(
    val title : String,
    val content : String,
    val timestamp : Long,
    val color : Int,
    @PrimaryKey val id : Int? = null
) {
    companion object {
        val noteColors = listOf(RedPink, RedOrange, LightGreen, Violet, BabyBlue)
    }
}
