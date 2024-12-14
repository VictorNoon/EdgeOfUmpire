package com.example.edgeofumpire.score_tracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.edgeofumpire.data.database.AppDatabase
import com.example.edgeofumpire.data.model.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MatchCreationScreen(
    modifier: Modifier = Modifier,
    onMatchCreated: (Match) -> Unit
    ) {

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var player1Name by remember { mutableStateOf("") }
        var player2Name by remember { mutableStateOf("") }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Joueur 1: ")
            TextField(value = player1Name,
                onValueChange = { player1Name = it })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Joueur 2: ")
            TextField(value = player2Name,
                onValueChange = { player2Name = it })
        }
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val match = Match.startNewMatch(player1Name, player2Name)
                val newUID = AppDatabase.getDataBase().matchDao().insert(match)
                withContext(Dispatchers.Main) {
                    onMatchCreated(match.copy(uid = newUID))
                }
            }
        }, enabled = player1Name.isNotEmpty() && player2Name.isNotEmpty()) {
            Text("Commencer le match")
        }
    }
}