package com.oussamateyib.thoth.feature.settings.impl.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.feature.settings.api.SettingsNavKey
import com.oussamateyib.thoth.feature.settings.impl.SettingsScreen
import com.oussamateyib.thoth.feature.settings.impl.SettingsViewModel

// Map the navigation key to the screen composable
fun EntryProviderScope<NavKey>.settingsEntry(
    navigator: Navigator
) {
    entry<SettingsNavKey> {
        val viewModel = hiltViewModel<SettingsViewModel>()

        SettingsScreen(
            onBackClick = navigator::goBack,
            viewModel = viewModel
        )
    }
}