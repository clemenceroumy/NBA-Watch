package com.croumy.nbawatch.presentation.ui.game

import Game
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.croumy.nbawatch.presentation.data.LiveService
import com.croumy.nbawatch.presentation.models.enums.GameStatus
import com.croumy.nbawatch.presentation.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class GameDetailsViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val gameId = savedStateHandle.get<String>(NavRoutes.GameDetails.gameId)

    private val liveService = LiveService()

    val game: MutableState<Game?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            getGame()

            if(game.value != null && game.value!!.gameStatusValue == GameStatus.LIVE) {
                while (true) {
                    delay(60.seconds)
                    getGame()
                }
            }
        }
    }

    suspend fun getGame() {
        val result = liveService.getTodayGames()
        game.value = result?.scoreboard?.games?.find { it.gameId == gameId }
    }
}