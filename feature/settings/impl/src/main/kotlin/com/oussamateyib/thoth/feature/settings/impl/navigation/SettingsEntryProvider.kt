package com.oussamateyib.thoth.feature.settings.impl.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.feature.settings.api.LicensesNavKey
import com.oussamateyib.thoth.feature.settings.api.SettingsNavKey
import com.oussamateyib.thoth.feature.settings.impl.LicensesScreen
import com.oussamateyib.thoth.feature.settings.impl.SettingsScreen
import com.oussamateyib.thoth.feature.settings.impl.SettingsViewModel

// Map navigation keys to their corresponding screens
fun EntryProviderScope<NavKey>.settingsEntry(
    navigator: Navigator
) {
    entry<SettingsNavKey> {
        val viewModel = hiltViewModel<SettingsViewModel>()

        SettingsScreen(
            onBackClick = navigator::goBack,
            onLicensesClick = { navigator.navigate(LicensesNavKey) },
            viewModel = viewModel
        )
    }

    entry<LicensesNavKey> {
        LicensesScreen(onBackClick = navigator::goBack)
    }
}
