package com.nicolas.androidofflinefirst.network

import android.util.Log
import com.nicolas.androidofflinefirst.network.services.GameService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

const val TIMEOUT_IN_SECONDS = 6L

object NetworkClient {

    private fun okHttpClientBuilder() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d(("OkHttp"), it)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    fun retrofitBuilder(
        baseUrl: String,
        okHttpClient: OkHttpClient = okHttpClientBuilder()
    ): GameService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GameService::class.java)

}