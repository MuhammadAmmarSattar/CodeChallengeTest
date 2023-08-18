package com.muhammad.codechallengetest.module.callingModule.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.muhammad.codechallengetest.R
import com.muhammad.codechallengetest.module.callingModule.manager.DiagnosticConsentManager
import com.muhammad.codechallengetest.module.callingModule.presentation.viewmodel.UserConsentViewModel
import com.muhammad.codechallengetest.module.diagnosticConsentModule.DiagnosticConsent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConsentActivity : AppCompatActivity() {
    val id = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var viewModel: UserConsentViewModel
        // this viewmodel without factory i didnot get time to make a setup of dependency injection hilt
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent)




        lifecycleScope.launch {
            viewModel.consentFlags.collect { flags ->
                println("Consent Flags: $flags")
                //  the current consent flags
                // perform any actions or logic that depend on the current consent flags
            }
        }
        viewModel.getUserConsentFlags(userId = id)

        // Launch a coroutine within the lifecycle scope to collect and observe consent update events
        lifecycleScope.launch {
            viewModel.consentUpdates.collect { event ->
                // print the updated consent flags and timestamp for debugging purposes
                println("Consent Flags Updated: ${event.consentFlags}, Timestamp: ${event.timestamp}")

                // update your UI with the new consent event information


            }
        }
        // Retrieve consent flags using Retrofit (inside a coroutine scope)
        lifecycleScope.launch {
            val consentFlagsRetrofit = viewModel.getUserConsentFlagsRetrofit(id)
            println("Consent Flags (Retrofit): $consentFlagsRetrofit")
        }

        val updateButton: Button = findViewById(R.id.updateButton)
        updateButton.setOnClickListener {
            // set a click listener for the "Update" button to visualize user consent update
            updateButton.setOnClickListener {

                val updatedFlags = mapOf("flag_one" to true, "flag_two" to false)

                // Call the ViewModel updateUserConsent method to update user consent with the new flags
                viewModel.updateUserConsent(id, updatedFlags)
            }

        }
    }
}
