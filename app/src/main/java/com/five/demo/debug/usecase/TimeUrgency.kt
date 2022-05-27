package com.five.demo.debug.usecase

import com.five.demo.debug.timer.Timer
import com.five.demo.debug.timer.TimerMapper
import com.five.demo.debug.timer.Urgency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Singleton

@Singleton
class TimeUrgency(
    private val timer: Timer,
    private val timerMapper: TimerMapper
) {

    operator fun invoke(): Flow<Urgency> = timer.remainingTime().map { timerMapper.toUrgencyByTimeElapsed(System.currentTimeMillis() - it.value) }
}
