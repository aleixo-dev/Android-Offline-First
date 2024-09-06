package com.nicolas.androidofflinefirst.sync

import com.nicolas.androidofflinefirst.network.model.GamesResponseItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface Synchronizer {

    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)
}

interface Syncable {
    suspend fun syncWith(synchronizer: Synchronizer): Boolean
}

suspend fun Synchronizer.gameSync(
    ioDispatcher: CoroutineDispatcher,
    gameFetcher: suspend () -> Result<List<GamesResponseItem>>,
    gamePersistence: suspend (List<GamesResponseItem>) -> Boolean
): Boolean = withContext(ioDispatcher) {

    gameFetcher().fold(
        onSuccess = { games -> awaitAll(async { gamePersistence(games) }).all { it } },
        onFailure = { false }
    )
}