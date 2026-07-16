package com.oussamateyib.thoth.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import java.text.NumberFormat

@Composable
fun Int.toLocalizedFormat(): String {
    val configuration = LocalConfiguration.current
    val currentLocale = remember(configuration) {
        // Extract the primary preferred system locale
        configuration.locales[0]
    }

    val numberFormatter = remember(currentLocale) {
        // Initialize the locale-aware integer formatting instance
        NumberFormat.getIntegerInstance(currentLocale)
    }

    return remember(this, numberFormatter) {
        numberFormatter.format(this)
    }
}
