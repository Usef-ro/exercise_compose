package com.example.jetreader.Di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {



    @Singleton
    @Provides
    fun providerFireBaseAuth():FirebaseAuth{
        return  Firebase.auth
    }

    @Singleton
    @Provides
    fun providersFireBaseInstance():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

}
