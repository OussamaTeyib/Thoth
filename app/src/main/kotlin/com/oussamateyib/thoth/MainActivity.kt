package com.oussamateyib.thoth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.oussamateyib.thoth.navigation.ThothNavHost
import com.oussamateyib.thoth.ui.rememberThothAppState
import com.oussamateyib.thoth.ui.theme.ThothTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThothTheme {
                val appState = rememberThothAppState()
                ThothNavHost(appState = appState)
            }
        }
    }
}