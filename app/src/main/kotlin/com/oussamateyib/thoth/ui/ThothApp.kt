package com.oussamateyib.thoth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.oussamateyib.thoth.core.navigation.toEntries
import com.oussamateyib.thoth.feature.notes.navigation.notesEntry

@Composable
fun ThothApp(
    appState: ThothAppState,
    modifier: Modifier = Modifier
) {
    val entryProvider = entryProvider {
        notesEntry(appState.navigator)
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavDisplay(
            // Fetch UI screens matching the current navigation state
            entries = appState.navigationState.toEntries(entryProvider),
            onBack = { appState.navigator.goBack() },
        )
    }
}