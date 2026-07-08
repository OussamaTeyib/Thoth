package com.oussamateyib.thoth.core.ui

import androidx.compose.runtime.Composable
import com.oussamateyib.thoth.core.model.data.Language

@Composable
fun LanguageChooserDialog(
    onDismiss: () -> Unit,
    currentLanguage: Language,
    onLanguageSelected: (Language) -> Unit
) = SettingsDialog(
    titleRes = R.string.core_ui_choose_language,
    onDismiss = onDismiss
) {
    Language.entries.forEach {
        SettingsChooserRow(
            text = it.asLabel(),
            selected = it == currentLanguage,
            onClick = { onLanguageSelected(it) }
        )
    }
}