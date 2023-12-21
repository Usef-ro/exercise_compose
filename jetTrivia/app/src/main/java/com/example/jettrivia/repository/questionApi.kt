package com.example.jettrivia.repository

import com.example.jettrivia.domain.modelQuestion
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface questionApi {

    @GET("world.json")
    suspend fun getAllQuestion():modelQuestion

}