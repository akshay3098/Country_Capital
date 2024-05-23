package com.example.walmartassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.walmartassignment.repository.CountryRepo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryViewModelTest {


    private lateinit var viewModel: CountryViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // Ensure you're importing androidx.test.ext.junit.rules.*


    private lateinit var repo: CountryRepo

    @Mock
    private lateinit var selectedCountryObserver: Observer<String> // Change Observer<String> to LiveDataObserver<String>

    @Mock
    private lateinit var capitalObserver: Observer<String>

    @Before
    fun setup() {
        repo = CountryRepo()
        viewModel = CountryViewModel(repo)
        viewModel.selectedCountry.observeForever(selectedCountryObserver)
        viewModel.capital.observeForever(capitalObserver)
    }

    @Test
    fun selectCountry_updatesSelectedCountry() {
        val country = "India"
        viewModel.selectCountry(country)
        verify(selectedCountryObserver).onChanged("India")
        verify(capitalObserver).onChanged("New Delhi")
    }

    @After
    fun tearDown(){
        viewModel.capital.removeObserver(capitalObserver)
        viewModel.selectedCountry.removeObserver(selectedCountryObserver)
    }

}