package com.nicolas.androidofflinefirst.sync.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.nicolas.androidofflinefirst.data.repository.GameRepository
import com.nicolas.androidofflinefirst.sync.Synchronizer
import com.nicolas.androidofflinefirst.sync.initializers.SyncConstraints
import com.nicolas.androidofflinefirst.sync.initializers.syncForegroundInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class SyncWorker(
    private val context: Context,
    workerParams: WorkerParameters,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val gameRepository: GameRepository
) : CoroutineWorker(context, workerParams), Synchronizer {

    override suspend fun getForegroundInfo(): ForegroundInfo = context.syncForegroundInfo()

    override suspend fun doWork(): Result = withContext(ioDispatcher) {

        val syncSuccessfully = awaitAll(
            async { gameRepository.sync() },
        ).all { it }

        if (syncSuccessfully) Result.success() else Result.retry()
    }

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<SyncWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}