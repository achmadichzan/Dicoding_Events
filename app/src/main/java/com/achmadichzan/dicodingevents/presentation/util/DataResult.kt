package com.achmadichzan.dicodingevents.presentation.util

sealed class DataResult<out T> {
    data class Success<T>(val data: T): DataResult<T>()
    data class Error(val exception: Throwable): DataResult<Nothing>()
}
