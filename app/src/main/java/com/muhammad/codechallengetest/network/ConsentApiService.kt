package com.muhammad.codechallengetest.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ConsentApiService {
    @GET("/endpoint/{Id}/consent")
    suspend fun getUserConsentFlags(@Path("Id") id: String): Map<String, Boolean>
}
