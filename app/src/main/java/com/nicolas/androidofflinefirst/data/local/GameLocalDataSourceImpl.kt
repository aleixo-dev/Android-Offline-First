package com.nicolas.androidofflinefirst.data.local

import com.nicolas.androidofflinefirst.data.mapper.toEntity
import com.nicolas.androidofflinefirst.data.mapper.toModel
import com.nicolas.androidofflinefirst.database.dao.GameDao
import com.nicolas.androidofflinefirst.model.GameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameLocalDataSourceImpl(
    private val gameDao: GameDao
) : GameLocalDataSource {

    override fun getGames(): Flow<List<GameModel>> =
        gameDao.getAllGames().map { it.map { entity -> entity.toModel() } }

    override suspend fun saveGames(vararg game: GameModel) {
        game.forEach { gameDao.insertGame(it.toEntity()) }
    }
}