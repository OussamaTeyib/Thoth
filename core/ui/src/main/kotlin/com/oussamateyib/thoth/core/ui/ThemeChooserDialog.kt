package com.oussamateyib.thoth.core.ui

import androidx.compose.runtime.Composable
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig

@Composable
fun ThemeChooserDialog(
    onDismiss: () -> Unit,
    currentConfig: DarkThemeConfig,
    onConfigSelected: (DarkThemeConfig) -> Unit
) = SettingsDialog(
    titleRes = R.string.core_ui_choose_theme,
    onDismiss = onDismiss
) {
    DarkThemeConfig.entries.forEach {
        SettingsChooserRow(
            text = it.asLabel(),
            selected = it == currentConfig,
            onClick = { onConfigSelected(it) }
        )
    }
}