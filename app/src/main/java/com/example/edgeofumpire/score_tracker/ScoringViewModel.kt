package com.example.edgeofumpire.score_tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edgeofumpire.data.database.AppDatabase
import com.example.edgeofumpire.data.model.Match
import com.example.edgeofumpire.data.model.Winner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ScoringViewModel : ViewModel() {
    var matchFlow : MutableStateFlow<Match?> = MutableStateFlow(Match.startNewMatch("", ""))
        private set

    private var scores : MutableList<Match> = mutableListOf()

    fun getMatchFlow(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDataBase().matchDao().getById(id)!!.onEach {
                matchFlow.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun addPointToPlayerOne() {
        val match = matchFlow.value!!
        if (match.getWinner() != Winner.None) return
        CoroutineScope(Dispatchers.IO).launch {
            scores.add(match)
            AppDatabase.getDataBase().matchDao().insert(match.firstPlayerScored())
        }
    }

    fun addPointToPlayerTwo() {
        val match = matchFlow.value!!
        if (match.getWinner() != Winner.None) return
        CoroutineScope(Dispatchers.IO).launch {
            scores.add(match)
            AppDatabase.getDataBase().matchDao().insert(match.secondPlayerScored())
        }
    }

    fun cancelLastAction() {
        if (scores.isEmpty()) return
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDataBase().matchDao().insert(scores.removeAt(scores.lastIndex))
        }
    }
}