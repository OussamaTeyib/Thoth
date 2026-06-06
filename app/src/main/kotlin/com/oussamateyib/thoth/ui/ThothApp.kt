package com.oussamateyib.thoth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.core.navigation.toEntries
import com.oussamateyib.thoth.feature.notes.impl.navigation.notesEntry

@Composable
fun ThothApp(
    appState: ThothAppState,
    modifier: Modifier = Modifier
) {
    val navigator = remember { Navigator(appState.navigationState) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val entryProvider = entryProvider {
            notesEntry(navigator)
        }

        NavDisplay(
            // Provide entries for the current navigation state
            entries = appState.navigationState.toEntries(entryProvider),
            onBack = { navigator.goBack() }
        )
    }
}