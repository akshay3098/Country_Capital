package com.example.walmartassignment

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun isNetworkException(exception: Exception): Boolean {
    return when (exception) {
        is UnknownHostException -> true
        is SocketTimeoutException -> true
        is ConnectException -> true
        is IOException -> true
        else -> false
    }
}