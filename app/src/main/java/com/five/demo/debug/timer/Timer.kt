package com.five.demo.debug.timer

import kotlinx.coroutines.flow.Flow

data class RemainingTime(
    val value: Long
)

interface Timer {

    fun remainingTime(): Flow<RemainingTime>

    suspend fun start()

    suspend fun stop()
}
