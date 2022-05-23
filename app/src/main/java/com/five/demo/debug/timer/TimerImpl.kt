package com.five.demo.debug.timer

import com.five.demo.debug.preferences.TimerPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import org.koin.core.annotation.Singleton

@Singleton
class TimerImpl(private val timerPreferences: TimerPreferences) : Timer {

    @OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
    override fun remainingTime(): Flow<Timestamp> =
        timerPreferences.startMillis()
            .flatMapLatest { timestamp -> ticker(500L, 0L).receiveAsFlow().map { timestamp } }
            .map { Timestamp(it) }

    override suspend fun startTimer() {
        if (timerPreferences.startMillis().first() > 0) timerPreferences.start()
    }
}
