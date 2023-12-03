package com.example.jettrivia.di

import com.example.jettrivia.repository.questionApi
import com.example.jettrivia.util.constant.url_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class appModule {


    @Singleton
    @Provides
    fun provideQuestionApi():questionApi{
        return Retrofit.Builder()
            .baseUrl(url_BASE)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
            .create(questionApi::class.java)
    }
}