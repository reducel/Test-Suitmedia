package com.example.test_suitmedia.di

import android.content.Context
import com.example.test_suitmedia.data.repository.UserRepository
import com.example.test_suitmedia.data.retrofit.ApiConfig

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}