package com.nicolas.androidofflinefirst.data.util

sealed class ApiResponse<out T> {
    data class Success<out T>(val value: T) : ApiResponse<T>()
    data class Error<T>(val code: Int? = null, val error: Throwable? = null) : ApiResponse<T>()
}