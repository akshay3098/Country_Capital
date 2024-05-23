package com.example.walmartassignment.response

sealed class ApiResponse<out T> {
    object Loading : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
    data class Failure<out T>(val data: String) : ApiResponse<T>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
}