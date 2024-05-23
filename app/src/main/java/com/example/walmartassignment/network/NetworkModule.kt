package com.example.walmartassignment.network

import com.example.walmartassignment.api.ApiService
import com.example.walmartassignment.constants.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
This NetworkModule class is responsible for creating retrofit object
 */
class NetworkModule {

    /*
    Return the retrofit object
    */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /*
    Return the ApiService
     */
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}