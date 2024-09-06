package com.nicolas.androidofflinefirst.database.di

import com.nicolas.androidofflinefirst.database.ApplicationDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { ApplicationDatabase.roomDatabaseBuilder(context = androidApplication()) }
    single { get<ApplicationDatabase>().gameDao() }
}