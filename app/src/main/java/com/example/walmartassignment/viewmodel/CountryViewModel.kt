package com.example.walmartassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmartassignment.repository.CountryRepo

open class CountryViewModel(val repo : CountryRepo): ViewModel() {

    private val _selectedCountry = MutableLiveData<String>()
    val selectedCountry: LiveData<String> get() = _selectedCountry

    private val _capital = MutableLiveData<String>()
    val capital: LiveData<String> get() = _capital

    private val _countries = repo.getCountries()
    val countries: Map<String, String> get() = _countries

    fun selectCountry(country: String) {
        _selectedCountry.value = country
        _capital.postValue(repo.getCountries()[country])
    }


}