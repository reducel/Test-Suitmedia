package com.example.test_suitmedia.data.retrofit

import com.example.test_suitmedia.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): UserResponse
}