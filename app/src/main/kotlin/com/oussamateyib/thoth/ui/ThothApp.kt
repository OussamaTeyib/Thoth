package com.oussamateyib.thoth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oussamateyib.thoth.navigation.ThothNavHost

@Composable
fun ThothApp(
    appState: ThothAppState,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ThothNavHost(appState)
    }
}