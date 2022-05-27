package com.five.demo.debug.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.annotation.Singleton

private val KEY_TIMER_START = longPreferencesKey("timer_start")
private val KEY_TIMER_END = longPreferencesKey("timer_end")
private const val TIMER_DEFAULT = -1L

@Singleton
class TimerPreferencesImpl(private val preferences: DataStore<Preferences>) : TimerPreferences {
    private val startTimestampMillis = preferences.data
        .mapNotNull { it[KEY_TIMER_START] ?: TIMER_DEFAULT }

    private val endTimestampMillis = preferences.data
        .mapNotNull { it[KEY_TIMER_END] ?: TIMER_DEFAULT }

    override fun startMillis(): Flow<Long> = startTimestampMillis
    override fun endMillis(): Flow<Long> = endTimestampMillis

    override suspend fun stop() = setEndTime(System.currentTimeMillis())

    override suspend fun start() {
        setStartTime(System.currentTimeMillis())
        clearEndTime()
    }

    private suspend fun clearEndTime() = setEndTime(TIMER_DEFAULT)

    private suspend fun setStartTime(time: Long) {
        if (startTimestampMillis.first() > 0) return

        preferences.edit { it[KEY_TIMER_START] = time }
    }

    private suspend fun setEndTime(time: Long) {
        if (endTimestampMillis.first() > 0) return

        preferences.edit { it[KEY_TIMER_END] = time }
    }
}
