package com.nicolas.androidofflinefirst.data.repository

import com.nicolas.androidofflinefirst.model.GameModel
import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import com.nicolas.androidofflinefirst.sync.Syncable
import kotlinx.coroutines.flow.Flow

interface GameRepository : Syncable {

    suspend fun getRemoteGames(): Result<List<GamesResponseItem>>

    fun getLocalGames(): Flow<List<GameModel>>

    suspend fun saveGames(vararg game: GamesResponseItem)

}