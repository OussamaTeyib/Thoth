package com.oussamateyib.thoth.core.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThothNavigationDrawer(
    drawerState: DrawerState,
    drawerContent: ThothNavigationDrawerScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.width(280.dp)
        ) {
            Text(
                text = "Thoth",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            // Collect navigation items by calling the drawer content on the scope
            val scope = ThothNavigationDrawerScope().apply(drawerContent)
            // Render each collected navigation item sequentially
            scope.items.forEach { it() }
        }
    },
    modifier = modifier,
    content = content
)

class ThothNavigationDrawerScope(
    internal val items: MutableList<@Composable () -> Unit> = mutableListOf()
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        label: @Composable () -> Unit
    ) = items.add {
        NavigationDrawerItem(
            selected = selected,
            onClick = onClick,
            icon = icon,
            label = label,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}