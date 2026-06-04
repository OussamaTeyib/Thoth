package com.oussamateyib.thoth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.oussamateyib.thoth.core.navigation.NavigationState
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.core.navigation.rememberNavigationState
import com.oussamateyib.thoth.feature.notes.navigation.NoteListNavKey

@Stable
class ThothAppState(
    val navigationState: NavigationState,
    val navigator: Navigator
)

@Composable
fun rememberThothAppState(
    navigationState: NavigationState = rememberNavigationState(
        startRoute = NoteListNavKey,
        topLevelRoutes = setOf(NoteListNavKey),
    ),
    navigator: Navigator = remember { Navigator(navigationState) }
): ThothAppState = remember(navigationState, navigator) {
    ThothAppState(navigationState, navigator)
}