package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig

@Composable
fun ThemeChooserDialog(
    onDismiss: () -> Unit,
    currentConfig: DarkThemeConfig,
    onConfigSelected: (DarkThemeConfig) -> Unit
) = AlertDialog(
    onDismissRequest = onDismiss,
    modifier = Modifier.widthIn(max = 320.dp),
    title = {
        Text(
            text = stringResource(R.string.core_ui_choose_theme),
            style = MaterialTheme.typography.titleLarge
        )
    },
    text = {
        Column(
            Modifier.selectableGroup()
        ) {
            SettingsChooserRow(
                text = DarkThemeConfig.LIGHT.asLabel(),
                selected = currentConfig == DarkThemeConfig.LIGHT,
                onClick = { onConfigSelected(DarkThemeConfig.LIGHT) }
            )
            SettingsChooserRow(
                text = DarkThemeConfig.DARK.asLabel(),
                selected = currentConfig == DarkThemeConfig.DARK,
                onClick = { onConfigSelected(DarkThemeConfig.DARK) }
            )
            SettingsChooserRow(
                text = DarkThemeConfig.FOLLOW_SYSTEM.asLabel(),
                selected = currentConfig == DarkThemeConfig.FOLLOW_SYSTEM,
                onClick = { onConfigSelected(DarkThemeConfig.FOLLOW_SYSTEM) }
            )
        }
    },
    confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = stringResource(R.string.core_ui_dismiss_dialog_button_text))
        }
    }
)