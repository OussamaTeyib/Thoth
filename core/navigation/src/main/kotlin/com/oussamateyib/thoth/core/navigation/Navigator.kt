package com.oussamateyib.thoth.core.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {
    fun navigate(key: NavKey) {
        when (key) {
            state.currentTopLevelKey -> clearSubStack()
            in state.topLevelKeys -> goToTopLevel(key)
            else -> goToKey(key)
        }
    }

    fun goBack() {
        when (state.currentKey) {
            state.startKey -> error("You cannot go back from the start destination")
            state.currentTopLevelKey -> {
                // At base of current substack, return to previous top-level stack
                state.topLevelStack.removeLastOrNull()
            }

            else -> state.currentSubStack.removeLastOrNull()
        }
    }

    private fun goToKey(key: NavKey) {
        state.currentSubStack.apply {
            // Remove it if it's already in the stack so it's added at the end
            remove(key)
            add(key)
        }
    }

    private fun goToTopLevel(key: NavKey) {
        state.topLevelStack.apply {
            if (key == state.startKey) {
                // This is the start key. Clear the stack so it's added as the only key
                clear()
            } else {
                // Remove it if it's already in the stack so it's added at the end
                remove(key)
            }
            add(key)
        }
    }

    private fun clearSubStack() {
        state.currentSubStack.run {
            if (size > 1) subList(1, size).clear()
        }
    }
}
