package com.nicolas.androidofflinefirst.network.services

import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface GameService {

    @GET("games")
    suspend fun getGames() : Response<List<GamesResponseItem>>
}