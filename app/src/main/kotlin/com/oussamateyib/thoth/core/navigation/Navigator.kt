package com.oussamateyib.thoth.core.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        // Switch to the target destination if it is a top-level route
        if (route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            // Append the destination onto the active stack
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")
        val currentRoute = currentStack.last()

        // Switch to the default route if only the base screen remains
        if (currentRoute == state.topLevelRoute) {
            state.topLevelRoute = state.startRoute
        } else {
            // Remove the top screen from the active stack
            currentStack.removeLastOrNull()
        }
    }
}