package com.example.edgeofumpire.score_tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edgeofumpire.data.database.AppDatabase
import com.example.edgeofumpire.data.model.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MatchSelectionViewModel: ViewModel() {
    var matchesFlow : MutableStateFlow<List<Match>> = MutableStateFlow(emptyList())
        private set

    init {
        AppDatabase.getDataBase().matchDao().getAll().onEach {
            matchesFlow.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteMatch(match: Match) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDataBase().matchDao().deleteById(match.uid)
        }
    }
}