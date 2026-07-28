package com.oussamateyib.thoth.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.oussamateyib.thoth.feature.notes.api.NoteListNavKey
import com.oussamateyib.thoth.feature.settings.api.SettingsNavKey
import com.oussamateyib.thoth.core.designsystem.R as DesignR

data class TopLevelNavItem(
    @param:DrawableRes val iconId: Int,
    @param:StringRes val labelId: Int,
)

val NOTES = TopLevelNavItem(
    iconId = DesignR.drawable.core_designsystem_ic_notes,
    labelId = DesignR.string.core_designsystem_notes,
)

val Settings = TopLevelNavItem(
    iconId = DesignR.drawable.core_designsystem_ic_settings,
    labelId = DesignR.string.core_designsystem_settings,
)

val TOP_LEVEL_NAV_ITEMS = mapOf(
    NoteListNavKey to NOTES,
    SettingsNavKey to Settings,
)
