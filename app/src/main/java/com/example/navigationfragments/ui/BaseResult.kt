package com.example.navigationfragments.ui

import java.lang.Exception

sealed class BaseResult<T> {
    data class Success<T>(val data:T):BaseResult<T>()
    data class Error<T>(val exception: Exception):BaseResult<T>()
    object Loading:BaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}