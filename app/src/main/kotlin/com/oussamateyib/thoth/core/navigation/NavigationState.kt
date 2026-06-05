package com.oussamateyib.thoth.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>> // Hold a separate stack for each top-level route
) {
    // Hide the MutableState wrapper, expose the value directly
    var topLevelRoute: NavKey by topLevelRoute

    // Expose only stacks that should be rendered
    val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            // Render only the start stack
            listOf(startRoute)
        } else {
            // Keep the start stack alive and render the selected stack
            listOf(startRoute, topLevelRoute)
        }
}

@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    // Map each root key to its corresponding list of fully decorated UI screens
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            // Save and restore inner screen state (e.g., text inputs) during configuration changes
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            // Provide an isolated ViewModel container for each screen that clears automatically when popped
            rememberViewModelStoreNavEntryDecorator()
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }

    // Collect entries exclusively from stacks marked as active
    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}

@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationState {
    // Keep the active top-level route in memory and protect it from being cleared
    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        // Save the current route choice during configuration changes or process death
        serializer = MutableStateSerializer(NavKeySerializer())
    ) {
        // Set up the default route
        mutableStateOf(startRoute)
    }

    // Associate each top-level route with its own independent stack
    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}