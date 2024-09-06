package com.nicolas.androidofflinefirst.data.local

import com.nicolas.androidofflinefirst.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    fun getGames(): Flow<List<GameModel>>

    suspend fun saveGames(vararg game: GameModel)

}