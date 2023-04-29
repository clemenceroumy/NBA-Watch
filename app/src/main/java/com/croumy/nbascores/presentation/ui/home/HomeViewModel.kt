package com.croumy.nbascores.presentation.ui.home

import Game
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.croumy.nbascores.presentation.data.LiveService
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val liveService = LiveService()

    val games: MutableState<List<Game>> = mutableStateOf(emptyList())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)

    init { viewModelScope.launch { getGames() } }

    suspend fun getGames() {
        isLoading.value = true
        val result = liveService.getTodayGames()
        games.value = result?.scoreboard?.games ?: emptyList()
        isLoading.value = false
    }
}