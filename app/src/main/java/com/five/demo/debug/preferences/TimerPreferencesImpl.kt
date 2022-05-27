package com.five.demo.debug.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.annotation.Singleton

private val KEY_TIMER = longPreferencesKey("timer")

@Singleton
class TimerPreferencesImpl(private val preferences: DataStore<Preferences>) : TimerPreferences {
    private val startTimestampMillis = preferences.data
        .mapNotNull { it[KEY_TIMER] ?: -1 }

    override fun startMillis(): Flow<Long> = startTimestampMillis

    override suspend fun start() {
        preferences.edit {
            it[KEY_TIMER] = System.currentTimeMillis()
        }
    }
}
