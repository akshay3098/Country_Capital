package com.example.walmartassignment.model

/*
* All the details pertained to the particular country is stored in this class
* */
data class CountryItem(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)