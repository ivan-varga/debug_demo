package com.five.demo.debug.timer

import android.util.Log
import com.five.demo.debug.preferences.TimerPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import org.koin.core.annotation.Singleton

@Singleton
@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
class TimerImpl(private val timerPreferences: TimerPreferences) : Timer {
    private val isTimerActive = MutableStateFlow(false)

    private val remainingTimeFlow = isTimerActive
        .flatMapLatest {
            if (it) {
                timerPreferences.startMillis()
                    .flatMapLatest { timestamp -> ticker(500L, 0L).receiveAsFlow().map { timestamp } }
            } else {
                timerPreferences.startMillis()
            }
        }
        .onEach { Log.d("asdfg", "timestamp $it") }
        .map { Timestamp(it) }
        .onEach { Log.d("asdfg", "mapped timestamp $it") }

    override fun remainingTime(): Flow<Timestamp> = remainingTimeFlow

    override suspend fun start() {
        if (timerPreferences.startMillis().first() < 0) timerPreferences.start()
        isTimerActive.value = true
    }

    override suspend fun stop() {
        isTimerActive.value = false
    }
}
