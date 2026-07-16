package com.oussamateyib.thoth.feature.settings.impl

import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.Language

sealed class SettingsEvent {
    data class SetDynamicColorPreference(val dynamicColor: Boolean) : SettingsEvent()
    data class UpdateDarkThemeConfig(val config: DarkThemeConfig) : SettingsEvent()
    data class UpdateLanguage(val language: Language) : SettingsEvent()
}
