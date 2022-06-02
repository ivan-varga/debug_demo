package com.five.demo.debug.main

import androidx.lifecycle.ViewModel
import com.five.demo.debug.usecase.HasAgreedToTerms
import kotlinx.coroutines.flow.first
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(private val hasAgreedToTerms: HasAgreedToTerms) : ViewModel() {

    suspend fun hasAgreed(): Boolean = hasAgreedToTerms().first()
}
