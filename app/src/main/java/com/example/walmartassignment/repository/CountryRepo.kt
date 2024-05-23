package com.example.walmartassignment.repository

import com.example.walmartassignment.api.ApiService
import com.example.walmartassignment.result


/*
CountryRepo is responsible for fetch country list data
*/
class CountryRepo(val apiService: ApiService) {
    suspend fun getCountryList() = result {
        apiService.getCountires()
    }

}