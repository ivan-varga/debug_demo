package com.five.demo.debug.timer

import kotlinx.coroutines.flow.Flow

data class Timestamp(
    val value: Long
) {
    init {
        if (value > System.currentTimeMillis()) throw RuntimeException("The timestamp must be instantiated with millis in the past!")
    }
}

interface Timer {

    fun remainingTime(): Flow<Timestamp>

    suspend fun start()

    suspend fun stop()
}
