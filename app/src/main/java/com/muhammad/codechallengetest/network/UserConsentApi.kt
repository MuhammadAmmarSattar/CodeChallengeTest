package com.muhammad.codechallengetest.network


class UserConsentApi(private val consentApiService: ConsentApiService) {
    suspend fun getUserConsentFlags(id: String): Map<String, Boolean> {
        return consentApiService.getUserConsentFlags(id)
    }
}