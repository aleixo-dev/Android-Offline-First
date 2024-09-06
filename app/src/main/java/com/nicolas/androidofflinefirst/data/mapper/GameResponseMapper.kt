package com.nicolas.androidofflinefirst.data.mapper

import com.nicolas.androidofflinefirst.database.model.GameEntity
import com.nicolas.androidofflinefirst.model.GameModel
import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun GamesResponseItem.toDomainModel(
    ioDispatcher: CoroutineDispatcher
) = withContext(ioDispatcher) {
    return@withContext GameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate
    )
}

suspend fun List<GamesResponseItem>.toDomainModel(
    ioDispatcher: CoroutineDispatcher
): List<GameModel> = withContext(ioDispatcher) {

    val games = mutableListOf<GameModel>()
    this@toDomainModel.forEach {
        games.add(it.toDomainModel(ioDispatcher))
    }
    return@withContext games
}

fun GameModel.toEntity() =
    GameEntity(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate
    )

fun GameEntity.toModel() =
    GameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate
    )