package com.nicolas.androidofflinefirst.data.di

import com.nicolas.androidofflinefirst.data.local.GameLocalDataSource
import com.nicolas.androidofflinefirst.data.local.GameLocalDataSourceImpl
import com.nicolas.androidofflinefirst.data.remote.GameRemoteDataSource
import com.nicolas.androidofflinefirst.data.remote.GameRemoteDataSourceImpl
import com.nicolas.androidofflinefirst.data.repository.GameRepository
import com.nicolas.androidofflinefirst.data.repository.GameRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<GameRepository> { GameRepositoryImpl(get(), get(), get()) }
    single<GameLocalDataSource> { GameLocalDataSourceImpl(get()) }
    single<GameRemoteDataSource> { GameRemoteDataSourceImpl(get()) }

}