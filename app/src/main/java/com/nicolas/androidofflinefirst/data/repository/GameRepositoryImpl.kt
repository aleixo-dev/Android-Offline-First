package com.nicolas.androidofflinefirst.data.repository

import com.nicolas.androidofflinefirst.data.local.GameLocalDataSource
import com.nicolas.androidofflinefirst.data.mapper.toDomainModel
import com.nicolas.androidofflinefirst.data.remote.GameRemoteDataSource
import com.nicolas.androidofflinefirst.model.GameModel
import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import com.nicolas.androidofflinefirst.sync.Synchronizer
import com.nicolas.androidofflinefirst.sync.gameSync
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : GameRepository {

    override suspend fun getRemoteGames(): Result<List<GamesResponseItem>> =
        remoteDataSource.getGames()

    override fun getLocalGames(): Flow<List<GameModel>> = localDataSource.getGames()

    override suspend fun saveGames(vararg game: GamesResponseItem) {
        localDataSource.saveGames(*game.map {
            it.toDomainModel(ioDispatcher)
        }.toTypedArray())
    }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.gameSync(
            ioDispatcher = ioDispatcher,
            gameFetcher = ::getRemoteGames,
            gamePersistence = ::insertLocalGames
        )

    private suspend fun insertLocalGames(games: List<GamesResponseItem>): Boolean {
        return try {
            saveGames(*games.toTypedArray())
            true
        } catch (exception: Exception) {
            false
        }
    }
}