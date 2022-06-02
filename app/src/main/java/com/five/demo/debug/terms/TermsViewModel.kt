package com.five.demo.debug.terms

import androidx.lifecycle.ViewModel
import com.five.demo.debug.usecase.AgreeToTerms
import kotlinx.coroutines.*
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TermsViewModel(private val agreedToTerms: AgreeToTerms) : ViewModel() {
    private val bgScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun agree() {
        bgScope.launch {
            agreedToTerms()
        }
    }

    override fun onCleared() {
        bgScope.cancel()

        super.onCleared()
    }
}
