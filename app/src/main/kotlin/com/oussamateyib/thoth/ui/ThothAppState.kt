package com.oussamateyib.thoth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.oussamateyib.thoth.core.navigation.NavigationState
import com.oussamateyib.thoth.core.navigation.rememberNavigationState
import com.oussamateyib.thoth.feature.notes.api.NoteListNavKey

@Stable
class ThothAppState(
    val navigationState: NavigationState
)

@Composable
fun rememberThothAppState(
    navigationState: NavigationState = rememberNavigationState(
        startKey = NoteListNavKey,
        topLevelKeys = setOf(NoteListNavKey)
    )
): ThothAppState = remember(navigationState) {
    ThothAppState(navigationState)
}