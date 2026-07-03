package com.oussamateyib.thoth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.oussamateyib.thoth.core.designsystem.theme.ThothTheme
import com.oussamateyib.thoth.ui.ThothApp
import com.oussamateyib.thoth.ui.rememberThothAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            // Stop collecting when the app is in the background, restart when visible
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    // Update local state on every new emission
                    .onEach { uiState = it }
                    .collect()
            }
        }

        enableEdgeToEdge()

        setContent {
            val appState = rememberThothAppState()
            val darkTheme = uiState.shouldUseDarkTheme(isSystemInDarkTheme())

            ThothTheme(darkTheme = darkTheme) {
                ThothApp(appState)
            }
        }
    }
}