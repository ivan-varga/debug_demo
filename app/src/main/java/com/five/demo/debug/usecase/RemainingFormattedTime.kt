package com.five.demo.debug.usecase

import com.five.demo.debug.timer.Timer
import com.five.demo.debug.timer.TimerMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Singleton

@Singleton
class RemainingFormattedTime(
    private val timer: Timer,
    private val timerMapper: TimerMapper
) {

    operator fun invoke(): Flow<String> =
        timer.remainingTime()
            .map { timerMapper.toFormattedTime(it.value) }
            .distinctUntilChanged()
}
