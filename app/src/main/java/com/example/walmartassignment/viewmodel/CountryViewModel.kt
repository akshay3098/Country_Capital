package com.example.walmartassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartassignment.model.Country
import com.example.walmartassignment.repository.CountryRepo
import com.example.walmartassignment.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


/*
* CountryViewModel is responsible for getting the data from CountryRepo
* */
class CountryViewModel(val repo: CountryRepo) : ViewModel() {

    val countries = MutableStateFlow<ApiResponse<Country?>>(ApiResponse.Empty)
    fun getCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCountryList().collect {
                countries.emit(it)
            }
        }
    }
}