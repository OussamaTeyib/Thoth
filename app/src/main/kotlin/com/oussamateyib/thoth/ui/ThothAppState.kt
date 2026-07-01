package com.oussamateyib.thoth.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.oussamateyib.thoth.core.navigation.NavigationState
import com.oussamateyib.thoth.core.navigation.rememberNavigationState
import com.oussamateyib.thoth.feature.notes.api.NoteListNavKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class ThothAppState(
    val navigationState: NavigationState,
    private val coroutineScope: CoroutineScope,
    val drawerState: DrawerState
) {
    val isCurrentDestinationTopLevel: Boolean
        get() = navigationState.currentKey in navigationState.topLevelKeys

    fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    fun closeDrawer() {
        coroutineScope.launch {
            drawerState.close()
        }
    }
}

@Composable
fun rememberThothAppState(
    navigationState: NavigationState = rememberNavigationState(
        startKey = NoteListNavKey,
        topLevelKeys = setOf(NoteListNavKey)
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) = remember(navigationState, coroutineScope, drawerState) {
    ThothAppState(navigationState, coroutineScope, drawerState)
}