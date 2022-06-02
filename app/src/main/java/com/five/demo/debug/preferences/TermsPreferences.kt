package com.five.demo.debug.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Singleton

private val KEY_TERMS = booleanPreferencesKey("key_terms")

interface TermsPreferences {

    fun hasAgreedToTerms(): Flow<Boolean>

    suspend fun agreeToTerms()
}

@Singleton
class TermsPreferencesImpl(private val preferences: DataStore<Preferences>) : TermsPreferences {

    override fun hasAgreedToTerms(): Flow<Boolean> = preferences.data.map { it[KEY_TERMS] ?: false }

    override suspend fun agreeToTerms() {
        preferences.edit { it[KEY_TERMS] = true }
    }

}
