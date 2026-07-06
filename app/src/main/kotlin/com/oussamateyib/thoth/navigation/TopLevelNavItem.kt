package com.oussamateyib.thoth.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.oussamateyib.thoth.feature.notes.api.NoteListNavKey
import com.oussamateyib.thoth.feature.settings.api.SettingsNavKey
import com.oussamateyib.thoth.feature.notes.api.R as NotesR
import com.oussamateyib.thoth.feature.settings.api.R as SettingsR

data class TopLevelNavItem(
    @param:DrawableRes val iconId: Int,
    @param:StringRes val labelId: Int
)

val NOTES = TopLevelNavItem(
    iconId = NotesR.drawable.feature_notes_api_icon,
    labelId = NotesR.string.feature_notes_api_title
)

val Settings = TopLevelNavItem(
    iconId = SettingsR.drawable.feature_settings_api_icon,
    labelId = SettingsR.string.feature_settings_api_title
)

val TOP_LEVEL_NAV_ITEMS = mapOf(
    NoteListNavKey to NOTES,
    SettingsNavKey to Settings
)