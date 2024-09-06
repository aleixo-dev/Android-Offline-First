package com.nicolas.androidofflinefirst.sync.di

import com.nicolas.androidofflinefirst.sync.SyncManager
import com.nicolas.androidofflinefirst.sync.status.WorkManagerSyncManager
import com.nicolas.androidofflinefirst.sync.workers.SyncWorker
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val syncModule = module {

    single<SyncManager> { WorkManagerSyncManager(get()) }
    worker {
        SyncWorker(
            context = androidApplication(),
            workerParams = get(),
            ioDispatcher = get(),
            gameRepository = get()
        )
    }
}