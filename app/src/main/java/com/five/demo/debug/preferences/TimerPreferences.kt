package com.five.demo.debug.preferences

import kotlinx.coroutines.flow.Flow

interface TimerPreferences {

    fun startMillis(): Flow<Long>

    suspend fun start()
}
