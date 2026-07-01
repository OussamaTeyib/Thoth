package com.oussamateyib.thoth.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.oussamateyib.thoth.core.designsystem.theme.extendedColorScheme
import com.oussamateyib.thoth.core.model.data.NoteColor

@Composable
fun NoteColor.asColor(): Color {
    val extended = MaterialTheme.extendedColorScheme
    return when (this) {
        NoteColor.Default -> MaterialTheme.colorScheme.surfaceContainer
        NoteColor.ButterYellow -> extended.butterYellow.colorContainer
        NoteColor.PeachCream -> extended.peachCream.colorContainer
        NoteColor.CoralMist -> extended.coralMist.colorContainer
        NoteColor.RoseBlush -> extended.roseBlush.colorContainer
        NoteColor.Lavender -> extended.lavender.colorContainer
        NoteColor.Periwinkle -> extended.periwinkle.colorContainer
        NoteColor.SkyBlue -> extended.skyBlue.colorContainer
        NoteColor.MintGreen -> extended.mintGreen.colorContainer
        NoteColor.Sage -> extended.sage.colorContainer
        NoteColor.AquaMist -> extended.aquaMist.colorContainer
        NoteColor.SilverCloud -> extended.silverCloud.colorContainer
    }
}

@Composable
fun NoteColor.asLabel(): String = stringResource(
    when (this) {
        NoteColor.Default -> R.string.core_ui_note_color_default
        NoteColor.ButterYellow -> R.string.core_ui_note_color_butter_yellow
        NoteColor.PeachCream -> R.string.core_ui_note_color_peach_cream
        NoteColor.CoralMist -> R.string.core_ui_note_color_coral_mist
        NoteColor.RoseBlush -> R.string.core_ui_note_color_rose_blush
        NoteColor.Lavender -> R.string.core_ui_note_color_lavender
        NoteColor.Periwinkle -> R.string.core_ui_note_color_periwinkle
        NoteColor.SkyBlue -> R.string.core_ui_note_color_sky_blue
        NoteColor.MintGreen -> R.string.core_ui_note_color_mint_green
        NoteColor.Sage -> R.string.core_ui_note_color_sage
        NoteColor.AquaMist -> R.string.core_ui_note_color_aqua_mist
        NoteColor.SilverCloud -> R.string.core_ui_note_color_silver_cloud
    }
)