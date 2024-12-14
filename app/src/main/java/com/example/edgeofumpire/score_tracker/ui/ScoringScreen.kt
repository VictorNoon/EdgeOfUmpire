package com.example.edgeofumpire.score_tracker.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edgeofumpire.data.model.Match
import com.example.edgeofumpire.data.model.toPrettyDisplay

@Composable
fun ScoringScreen(
    modifier: Modifier = Modifier,
    match: Match,
    onFirstPlayerScore: () -> Unit,
    onSecondPlayerScore: () -> Unit,
    onUndoAction: () -> Unit
) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text("Match ${match.firstPlayerName} vs ${match.secondPlayerName}",
                fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(20.dp))
        }
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Player 1: ${match.firstPlayerName}")
            Spacer(modifier = Modifier.padding(20.dp))
            Button(onClick = onFirstPlayerScore) {
                Text("P1 Scored")
            }
        }
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Player 2: ${match.secondPlayerName}")
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = onSecondPlayerScore) {
                    Text("P2 Scored")
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(onClick = onUndoAction) {
                Text("Annuler la dernière action")
            }
        }
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
        LazyRow {
            item {
                Column {
                    Text("Player 1:")
                    Text("Player 2:")
                }
            }
            itemsIndexed(match.playerScores) { i, score ->
                Spacer(Modifier.padding(20.dp))
                Column {
                    Text("G${i + 1}: ${score.firstPlayerScore.toPrettyDisplay()}")
                    Text("G${i + 1}: ${score.secondPlayerScore.toPrettyDisplay()}")
                }
            }
        }
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
        LazyColumn {
            itemsIndexed(match.playerScores.reversed()) { i, score ->
                Spacer(Modifier.padding(20.dp))
                Column {
                    Text("Player 1 Game ${match.playerScores.size - i}: ${score.firstPlayerScore.toPrettyDisplay()}")
                    Text("Player 2 Game ${match.playerScores.size - i}: ${score.secondPlayerScore.toPrettyDisplay()}")
                }
            }
        }

        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text("Player 2: ${match.secondPlayerName}")
                Spacer(modifier = Modifier.padding(20.dp))
                Button(onClick = onSecondPlayerScore) {
                    Text("P2 Scored")
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(onClick = onUndoAction) {
                Text("Annuler la dernière action")
            }
        }
    }
}

@Composable
@Preview(Devices.PHONE, showBackground = true)
fun ScoringScreenPreviewPhone() {
    val match = Match.startNewMatch("Player 1 Name", "Player 2 Name")
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()

    ScoringScreen(
        modifier = Modifier.fillMaxWidth(),
        match = match,
        onFirstPlayerScore = {},
        onSecondPlayerScore = {},
        onUndoAction = {}
    )
}

@Composable
@Preview(Devices.TABLET, showBackground = true)
fun ScoringScreenPreviewTablet() {
    val match = Match.startNewMatch("Player 1 Name", "Player 2 Name")
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .secondPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .firstPlayerScored()
        .secondPlayerScored()
    ScoringScreen(
        modifier = Modifier.fillMaxWidth(),
        match = match,
        onFirstPlayerScore = {},
        onSecondPlayerScore = {},
        onUndoAction = {}
    )
}