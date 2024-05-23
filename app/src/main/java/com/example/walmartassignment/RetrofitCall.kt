package com.example.walmartassignment

import com.example.walmartassignment.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/*
* Responsible for handling error events.
* */
fun <T> result(call: suspend () -> Response<T>): Flow<ApiResponse<T?>> = flow {
    emit(ApiResponse.Loading)
    try {
        val response = call()
        response.let {
            if (it.isSuccessful) {
                if (it.body() != null) {
                    emit(ApiResponse.Success(it.body()))
                } else {
                    emit(ApiResponse.Failure("Please Try Again"))
                }
            } else {
                emit(ApiResponse.Failure("Please Try Again"))
            }
        }

    } catch (ex: Exception) {
        if (isNetworkException(ex)) {
            emit(ApiResponse.Failure("Network error occurred"))
        } else {
            emit(ApiResponse.Failure(ex.message.toString()))
        }
    }
}