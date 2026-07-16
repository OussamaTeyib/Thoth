package com.oussamateyib.thoth.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig

@Composable
fun DarkThemeConfig.asLabel() = stringResource(
    when (this) {
        DarkThemeConfig.FOLLOW_SYSTEM -> R.string.core_ui_dark_theme_config_system_default
        DarkThemeConfig.LIGHT -> R.string.core_ui_dark_theme_config_light
        DarkThemeConfig.DARK -> R.string.core_ui_dark_theme_config_dark
    },
)
