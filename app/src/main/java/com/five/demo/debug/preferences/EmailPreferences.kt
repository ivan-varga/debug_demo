package com.five.demo.debug.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Singleton

private val KEY_EMAIL = stringPreferencesKey("key_email")

interface EmailPreferences {

    fun email(): Flow<String>

    suspend fun setEmail(email: String)
}

@Singleton
class EmailPreferencesImpl(private val preferences: DataStore<Preferences>) : EmailPreferences {
    override fun email(): Flow<String> = preferences.data.map { it[KEY_EMAIL] ?: "" }

    override suspend fun setEmail(email: String) {
        preferences.edit { it[KEY_EMAIL] = email }
    }

}
