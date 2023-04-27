package com.croumy.nbascores.presentation.ui

import Game
import ScoreboardResponse
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.croumy.nbascores.presentation.data.LiveService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val liveService = LiveService()

    val games: MutableState<List<Game>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            getGames()
        }
    }

    private suspend fun getGames() {
        val result = liveService.getTodayGames()
        games.value = result?.scoreboard?.games ?: emptyList()
    }
}