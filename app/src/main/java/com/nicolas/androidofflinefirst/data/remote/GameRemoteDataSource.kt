package com.nicolas.androidofflinefirst.data.remote

import com.nicolas.androidofflinefirst.network.model.GamesResponseItem

interface GameRemoteDataSource {
    suspend fun getGames(): Result<List<GamesResponseItem>>
}