package com.example.edgeofumpire.score_tracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.edgeofumpire.data.model.Match

@Composable
fun MatchSelectionScreen(
    modifier: Modifier = Modifier,
    matches: List<Match>,
    onSelectMatch: (Match) -> Unit,
    onDeleteMatch: (Match) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Selection des matchs")
        LazyColumn(modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End) {
            items(matches) {
                Row(Modifier.fillMaxWidth()
                    .padding(2.dp,)
                    .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text("Match ${it.firstPlayerName} vs ${it.secondPlayerName}")
                    }
                    Column(
                        modifier = Modifier.padding(end = 5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { onSelectMatch(it) }) {
                            Text("Ouvrir")
                        }
                        Button(onClick = { onDeleteMatch(it) }) {
                            Text("Supprimer")
                        }
                    }
                }
            }
        }
    }
}