package com.oussamateyib.thoth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.oussamateyib.thoth.core.designsystem.components.ThothNavigationDrawer
import com.oussamateyib.thoth.core.navigation.Navigator
import com.oussamateyib.thoth.core.navigation.toEntries
import com.oussamateyib.thoth.feature.notes.impl.navigation.notesEntry
import com.oussamateyib.thoth.navigation.TOP_LEVEL_NAV_ITEMS
import kotlinx.coroutines.launch

@Composable
fun ThothApp(
    appState: ThothAppState,
    modifier: Modifier = Modifier
) {
    val navigator = remember { Navigator(appState.navigationState) }

    ThothNavigationDrawer(
        drawerState = appState.drawerState,
        drawerContent = {
            TOP_LEVEL_NAV_ITEMS.forEach { (key, item) ->
                item(
                    selected = key == appState.navigationState.currentTopLevelKey,
                    onClick = {
                        navigator.navigate(key)
                        appState.coroutineScope.launch {
                            appState.drawerState.close()
                        }
                    },
                    label = {
                        Text(text = stringResource(item.labelId))
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.iconId),
                            contentDescription = stringResource(item.labelId)
                        )
                    }
                )
            }
        },
        modifier = modifier
    ) {
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
}