package com.five.demo.debug.preferences

import kotlinx.coroutines.flow.Flow

interface TimerPreferences {

    fun startMillis(): Flow<Long>

    fun endMillis(): Flow<Long>

    suspend fun start()

    suspend fun stop()
}
