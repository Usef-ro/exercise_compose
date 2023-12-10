package com.example.jetweather.repository

import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface weatherApi {

    @GET(value="/data/3.0/forecast/daily")
    suspend fun getWeather()

}