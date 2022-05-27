package com.five.demo.debug.timer

import com.five.demo.debug.preferences.TimerPreferences
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import org.koin.core.annotation.Singleton

@Singleton
@OptIn(ObsoleteCoroutinesApi::class)
class TimerImpl(private val timerPreferences: TimerPreferences) : Timer {

    private val tickerFlow = ticker(100L, 0L).receiveAsFlow()

    private val remainingTimeFlow =
        combine(
            timerPreferences.endMillis(),
            timerPreferences.startMillis(),
            tickerFlow
        ) { end, start, _ ->
            if (end < 0) System.currentTimeMillis() - start
            else end - start
        }
            .map { RemainingTime(it) }

    override fun remainingTime(): Flow<RemainingTime> = remainingTimeFlow

    override suspend fun start() = timerPreferences.start()

    override suspend fun stop() = timerPreferences.stop()
}
