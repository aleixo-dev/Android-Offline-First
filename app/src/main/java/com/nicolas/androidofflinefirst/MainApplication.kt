package com.nicolas.androidofflinefirst

import android.app.Application
import com.nicolas.androidofflinefirst.data.di.dataModule
import com.nicolas.androidofflinefirst.database.di.databaseModule
import com.nicolas.androidofflinefirst.di.appModule
import com.nicolas.androidofflinefirst.network.di.networkModule
import com.nicolas.androidofflinefirst.sync.di.syncModule
import com.nicolas.androidofflinefirst.sync.initializers.Sync
import com.nicolas.androidofflinefirst.sync.workers.SyncWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            workManagerFactory()
            modules(
                dataModule,
                networkModule,
                syncModule,
                databaseModule,
                appModule
            )
        }
        Sync.initialize(context = this@MainApplication)
    }
}