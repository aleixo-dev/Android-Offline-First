package com.nicolas.androidofflinefirst.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.androidofflinefirst.data.repository.GameRepository
import com.nicolas.androidofflinefirst.model.GameModel
import com.nicolas.androidofflinefirst.sync.SyncManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface HomeUiState {
    data class Success(val games: List<GameModel>) : HomeUiState
    data object Loading : HomeUiState
    data object Error : HomeUiState
}

class HomeViewModel(
    gameRepository: GameRepository,
    syncManager: SyncManager
) : ViewModel() {

    val isSyncing = syncManager.isSyncing
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val uiState = gameRepository.getLocalGames()
        .distinctUntilChanged()
        .map<List<GameModel>, HomeUiState>(HomeUiState::Success)
        .catch { emit(HomeUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )
}