package com.nicolas.androidofflinefirst.network.di

import com.nicolas.androidofflinefirst.BuildConfig
import com.nicolas.androidofflinefirst.network.NetworkClient.retrofitBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val networkModule = module {
    single { retrofitBuilder(baseUrl = BuildConfig.BASE_URL) }
    single<CoroutineDispatcher> { Dispatchers.IO }
}