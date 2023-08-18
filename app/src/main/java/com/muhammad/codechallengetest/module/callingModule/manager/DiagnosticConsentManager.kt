package com.muhammad.codechallengetest.module.callingModule.manager

import com.muhammad.codechallengetest.data.ConsentEvent
import com.muhammad.codechallengetest.module.diagnosticConsentModule.DiagnosticConsent
import com.muhammad.codechallengetest.network.ConsentApiService
import com.muhammad.codechallengetest.network.RetrofitClient
import com.muhammad.codechallengetest.network.UserConsentApi
import kotlinx.coroutines.flow.SharedFlow


// DiagnosticConsentManager is a facade pattern working repository. encapsulate the logic means => hide complex implementation
//We can achieve loose coupling between subsystems and their users.
class DiagnosticConsentManager (private val diagnosticConsent: DiagnosticConsent) {
    private val userConsentApi =
        UserConsentApi(RetrofitClient.retrofit.create(ConsentApiService::class.java))

    // fetches the users consent flags from the diagnosticConsent module
    fun getUserConsentFlags(userId: String): Boolean {
        return diagnosticConsent.getUserConsentFlag(userId)
    }
    // get updates to user consent events from the diagnosticConsent module
    fun getUserConsentUpdates(): SharedFlow<ConsentEvent> {
        return diagnosticConsent.getUserConsentUpdates()
    }
    // fetches the users consent flags from a remote data source using Retrofit library
    suspend fun getUserConsentFlagsRetrofit(userId: String): Map<String, Boolean> {
        return userConsentApi.getUserConsentFlags(userId)
    }
    // Updates the usesr consent flags with the help of  diagnosticConsent module
    fun updateUserConsent(userId: String, flags: Map<String, Boolean>) {
        diagnosticConsent.updateUserConsent(userId, flags)
    }
}