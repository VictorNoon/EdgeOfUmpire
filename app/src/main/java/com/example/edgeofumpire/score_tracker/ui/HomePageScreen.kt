package com.example.edgeofumpire.score_tracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
    onNavigateToMatchCreation: () -> Unit,
    onNavigateToMatchSelection: () -> Unit
    ) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToMatchCreation) {
            Text("Create New Match")
        }
        Button(onClick = onNavigateToMatchSelection) {
            Text("See old Match")
        }
    }
}