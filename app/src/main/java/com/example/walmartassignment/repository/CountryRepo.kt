package com.example.walmartassignment.repository

open class CountryRepo {
    fun getCountries()= mutableMapOf<String,String>().apply {
        put("Select Country", "")
        put("Australia","Canberra")
        put("Austria","Vienna")
        put("Bangladesh","Dhaka")
        put("Belgium","Brussels")
        put("Canada","Ottawa")
        put("China","Beijing")
        put("Denmark","Copenhagen")
        put("Egypt","Cairo")
        put("Ethiopia","Addis Ababa")
        put("Finland","Helsinki")
        put("France","Paris")
        put("Germany","Berlin")
        put("Greece","Athens")
        put("Hungary","Budapest")
        put("Select Country", "")
        put("India","New Delhi")
        put("Japan","Tokyo")
        put("Korea, South","Seoul")
        put("Norway","Oslo")
        put("Qatar","Doha")
        put("Spain","Madrid")
        put("Thailand","Bangkok")
        put("United Kingdom","London")
        put("United States of America","Washington D.C.")

    }

}