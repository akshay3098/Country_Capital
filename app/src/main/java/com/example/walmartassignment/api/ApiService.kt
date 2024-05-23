package com.example.walmartassignment.api

import com.example.walmartassignment.model.Country
import retrofit2.Response
import retrofit2.http.GET

/*
* Creates Services that consumes Restful APIs
* */
interface ApiService {

    /*
    * this service fetches data from the URL and given end point
    * */
    @GET("countries.json")
    suspend fun getCountires(): Response<Country>
}