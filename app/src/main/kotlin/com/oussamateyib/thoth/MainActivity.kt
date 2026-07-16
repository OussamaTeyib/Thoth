package com.oussamateyib.thoth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.core.designsystem.theme.ThothTheme
import com.oussamateyib.thoth.ui.ThothApp
import com.oussamateyib.thoth.ui.rememberThothAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is MainActivityState.Loading
        }

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            if (uiState is MainActivityState.Success) {
                val appState = rememberThothAppState()
                val dynamicColor = uiState.shouldUseDynamicColor()
                val darkTheme = uiState.shouldUseDarkTheme(isSystemInDarkTheme())

                ThothTheme(
                    darkTheme = darkTheme,
                    dynamicColor = dynamicColor
                ) {
                    ThothApp(appState)
                }
            }
        }
    }
}
