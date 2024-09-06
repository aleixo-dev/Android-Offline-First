package com.nicolas.androidofflinefirst.data.remote

import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import com.nicolas.androidofflinefirst.network.services.GameService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRemoteDataSourceImpl(
    private val service: GameService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GameRemoteDataSource {

    override suspend fun getGames(): Result<List<GamesResponseItem>> = withContext(ioDispatcher) {
        runCatching { service.getGames().body() ?: emptyList() }
    }
}