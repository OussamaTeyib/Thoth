package com.oussamateyib.thoth.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.oussamateyib.thoth.core.model.data.Language

@Composable
fun Language.asLabel() = stringResource(
    when (this) {
        Language.FOLLOW_SYSTEM -> R.string.core_ui_language_system_default
        Language.ENGLISH -> R.string.core_ui_language_en
        Language.ARABIC -> R.string.core_ui_language_ar
    }
)