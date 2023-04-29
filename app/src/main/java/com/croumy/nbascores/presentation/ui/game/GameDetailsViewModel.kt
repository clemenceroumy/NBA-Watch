package com.croumy.nbascores.presentation.ui.game

import Game
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.croumy.nbascores.presentation.data.LiveService
import com.croumy.nbascores.presentation.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val gameId = savedStateHandle.get<String>(NavRoutes.GameDetails.gameId)

    private val liveService = LiveService()

    val game: MutableState<Game?> = mutableStateOf(null)

    init { viewModelScope.launch { getGame() } }

    suspend fun getGame() {
        val result = liveService.getTodayGames()
        game.value = result?.scoreboard?.games?.find { it.gameId == gameId }
    }
}