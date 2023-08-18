package com.muhammad.codechallengetest.module.callingModule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.codechallengetest.data.ConsentEvent
import com.muhammad.codechallengetest.module.callingModule.manager.DiagnosticConsentManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

// diagnostic cosnenttt manager DiagnosticConsentManager needs dependency of diagnosticConsent we can manage by DI hilt.
class UserConsentViewModel (private val diagnosticConsentManager: DiagnosticConsentManager) : ViewModel() {
    private val _consentFlags = MutableStateFlow<Boolean>(false)
    val consentFlags: Flow<Boolean> = _consentFlags

    private val _consentUpdates = MutableSharedFlow<ConsentEvent>()
    // i use sharedFlow instead of rxBus because its native approach
    val consentUpdates: SharedFlow<ConsentEvent> = _consentUpdates

    //function is responsible for updating the user's consent flags using the DiagnosticConsentManager.
    fun updateUserConsent(userId: String, flags: Map<String, Boolean>) {
        diagnosticConsentManager.updateUserConsent(userId, flags)
    }

    /**responsible of updating the _consentFlag notify in the view with the help of _consentFalgs*/
    fun getUserConsentFlags(userId: String){
        _consentFlags.value =  diagnosticConsentManager.getUserConsentFlags(userId)
    }

    init {
        // Observe user consent updates from DiagnosticConsentManager and emit them to SharedFlow
        //function from the DiagnosticConsentManager is observed using the collect function.
        // This function waits for updates to the user's consent and emits those updates to the _consentUpdates shared flow in the ViewModel.
        viewModelScope.launch {
            diagnosticConsentManager.getUserConsentUpdates().collect { consentEvent ->
                _consentUpdates.emit(consentEvent)
            }
        }
    }

    // it takes id idicating user for who want to retrieve the consent flags
    //fetching the user consent flags from the remote.
    suspend fun getUserConsentFlagsRetrofit(userId: String): Map<String, Boolean> {
        return diagnosticConsentManager.getUserConsentFlagsRetrofit(userId)
    }

    // for the local cache we can use data store or sharepreferences
}