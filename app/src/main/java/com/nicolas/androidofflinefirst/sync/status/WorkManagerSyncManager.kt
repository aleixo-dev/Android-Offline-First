package com.nicolas.androidofflinefirst.sync.status

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.nicolas.androidofflinefirst.sync.SyncManager
import com.nicolas.androidofflinefirst.sync.initializers.SyncWorkName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map

class WorkManagerSyncManager(context: Context) : SyncManager {
    override val isSyncing: Flow<Boolean> =
        WorkManager.getInstance(context).getWorkInfosForUniqueWorkFlow(SyncWorkName)
            .map(List<WorkInfo>::anyRunning)
            .conflate()
}

private fun List<WorkInfo>.anyRunning() = any { it.state == WorkInfo.State.RUNNING }