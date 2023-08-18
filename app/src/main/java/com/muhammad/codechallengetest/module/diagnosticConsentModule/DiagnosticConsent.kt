package com.muhammad.codechallengetest.module.diagnosticConsentModule

import com.muhammad.codechallengetest.data.ConsentEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class DiagnosticConsent {
    // for storing user flags consent Mutable map
    private val consentFlags: MutableMap<String, Boolean> = mutableMapOf()

    // SharedFlow to emit consent update events
    private val consentUpdates: MutableSharedFlow<ConsentEvent> = MutableSharedFlow()
    // updates the users consent flags and emits an update event
    fun updateUserConsent(userId: String, flags: Map<String, Boolean>) {
        consentFlags[userId] = flags[userId] ?: false
        val timestamp = System.currentTimeMillis()
        val consentEvent = ConsentEvent(flags, timestamp)
        consentUpdates.tryEmit(consentEvent)
    }

    // get the usesr consent flag for a specific user
    fun getUserConsentFlag(userId: String): Boolean {
        return consentFlags[userId] ?: false
    }
    // returns a sharedFlow to observe consent update events
    fun getUserConsentUpdates(): SharedFlow<ConsentEvent> {
        return consentUpdates.asSharedFlow()
    }
}
