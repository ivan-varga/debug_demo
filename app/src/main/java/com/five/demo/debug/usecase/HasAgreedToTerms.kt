package com.five.demo.debug.usecase

import com.five.demo.debug.preferences.TermsPreferences
import org.koin.core.annotation.Singleton

@Singleton
class HasAgreedToTerms(private val termsPreferences: TermsPreferences) {

    operator fun invoke() = termsPreferences.hasAgreedToTerms()
}
