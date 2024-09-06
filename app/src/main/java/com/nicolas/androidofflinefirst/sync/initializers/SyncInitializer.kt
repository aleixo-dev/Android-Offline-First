package com.nicolas.androidofflinefirst.sync.initializers

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.nicolas.androidofflinefirst.sync.workers.SyncWorker

object Sync {

    fun initialize(context: Context) {

        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork()
            )
        }
    }
}

internal const val SyncWorkName = "SyncWorkName"