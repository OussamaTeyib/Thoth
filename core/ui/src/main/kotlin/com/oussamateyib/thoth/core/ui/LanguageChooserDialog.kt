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

@Composable
fun LanguageChooserDialog(
    onDismiss: () -> Unit,
    currentLanguage: Language,
    onLanguageSelected: (Language) -> Unit
) = AlertDialog(
    onDismissRequest = onDismiss,
    modifier = Modifier.widthIn(max = 320.dp),
    title = {
        Text(
            text = stringResource(R.string.core_ui_choose_language),
            style = MaterialTheme.typography.titleLarge
        )
    },
    text = {
        Column(
            Modifier.selectableGroup()
        ) {
            Language.entries.forEach {
                SettingsChooserRow(
                    text = it.asLabel(),
                    selected = it == currentLanguage,
                    onClick = { onLanguageSelected(it) }
                )
            }
        }
    },
    confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = stringResource(R.string.core_ui_dismiss_dialog_button_text))
        }
    }
)