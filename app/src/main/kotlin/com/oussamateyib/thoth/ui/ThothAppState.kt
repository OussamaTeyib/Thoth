package com.oussamateyib.thoth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class ThothAppState(
    val navController: NavHostController
)

@Composable
fun rememberThothAppState(
    navController: NavHostController = rememberNavController()
): ThothAppState = remember(navController) {
    ThothAppState(navController)
}