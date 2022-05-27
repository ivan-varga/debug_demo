package com.five.demo.debug.usecase

import com.five.demo.debug.timer.Timer
import org.koin.core.annotation.Singleton

@Singleton
class StartTimer(private val timer: Timer) {

    suspend operator fun invoke() = timer.start()
}
