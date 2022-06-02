package com.five.demo.debug.usecase

import com.five.demo.debug.preferences.TermsPreferences
import org.koin.core.annotation.Singleton

@Singleton
class AgreeToTerms(private val termsPreferences: TermsPreferences) {

    suspend operator fun invoke() = termsPreferences.agreeToTerms()
}
