package com.oussamateyib.thoth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oussamateyib.thoth.features.notes.presentation.editor.NoteEditorScreen
import com.oussamateyib.thoth.features.notes.presentation.list.NoteListScreen
import com.oussamateyib.thoth.features.notes.presentation.util.NoteScreen
import com.oussamateyib.thoth.ui.theme.ThothTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThothTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NoteScreen.ListScreen.route
                    ) {
                        composable(
                            route = NoteScreen.ListScreen.route
                        ) {
                            NoteListScreen(navController = navController)
                        }
                        composable(
                            route = NoteScreen.EditorScreen.route + "?noteId={noteId}",
                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            NoteEditorScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}