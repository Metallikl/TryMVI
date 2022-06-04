package com.luche.trymvi.util

sealed class ResultStatus<out T>{
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val throwable: String) : ResultStatus<Nothing>()
}
