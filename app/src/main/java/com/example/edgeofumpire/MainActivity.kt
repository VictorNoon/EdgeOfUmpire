package com.example.edgeofumpire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.edgeofumpire.data.database.AppDatabase
import com.example.edgeofumpire.data.model.Match
import com.example.edgeofumpire.score_tracker.MatchSelectionViewModel
import com.example.edgeofumpire.score_tracker.ScoringViewModel
import com.example.edgeofumpire.score_tracker.ui.HomePageScreen
import com.example.edgeofumpire.score_tracker.ui.MatchCreationScreen
import com.example.edgeofumpire.score_tracker.ui.MatchSelectionScreen
import com.example.edgeofumpire.score_tracker.ui.ScoringScreen
import com.example.edgeofumpire.ui.theme.EdgeOfUmpireTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EdgeOfUmpireTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomePageScreen(
                                modifier = Modifier.padding(innerPadding),
                                onNavigateToMatchCreation = { navController.navigate("match_creation")  },
                                onNavigateToMatchSelection = { navController.navigate("saved_matches")}
                            )
                        }
                        composable("scoring/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    defaultValue = 0
                                    type = NavType.LongType
                                }
                            )
                        ) { navBackStackEntry ->
                            val viewModel: ScoringViewModel by viewModels()
                            val matchId = navBackStackEntry.arguments?.getLong("id")
                            println("Used match Pk = $matchId")
                            viewModel.getMatchFlow(matchId!!)
                            val match = viewModel.matchFlow.collectAsState()
                            match.value?.let { matchValue ->
                                ScoringScreen(
                                    modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                                    match = matchValue,
                                    onFirstPlayerScore = {
                                        CoroutineScope(Dispatchers.IO).launch { viewModel.addPointToPlayerOne()  }
                                    },
                                    onSecondPlayerScore = { viewModel.addPointToPlayerTwo() },
                                    onUndoAction = { viewModel.cancelLastAction() }
                                )
                            }

                        }
                        composable("saved_matches") {
                            val viewModel: MatchSelectionViewModel by viewModels()
                            val matches = viewModel.matchesFlow.collectAsState()
                            MatchSelectionScreen(
                                modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                                matches = matches.value,
                                onSelectMatch = { match -> navController.navigate("scoring/${match.uid}")  },
                                onDeleteMatch = { match -> viewModel.deleteMatch(match) }
                            )
                        }
                        composable("match_creation") {
                            MatchCreationScreen(modifier = Modifier.padding(innerPadding),
                                onMatchCreated = { match -> navController.navigate("scoring/${match.uid}") })
                        }
                    }
                }
            }
        }
    }
}