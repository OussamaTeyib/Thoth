package com.oussamateyib.thoth.feature.settings.impl

import com.oussamateyib.thoth.core.model.data.DarkThemeConfig

sealed class SettingsEvent {
    data class UpdateDarkThemeConfig(val config: DarkThemeConfig) : SettingsEvent()
}