package com.five.demo.debug.onboarding

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Timer
import com.five.demo.debug.timer.TimerMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OnBoardingViewModel(
    private val timer: Timer,
    private val timerMapper: TimerMapper
) : ViewModel() {

    private val bgScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        bgScope.launch {
            timer.startTimer()
        }
    }

    fun timeRemaining(): Flow<String> = timer.remainingTime().map { timerMapper.toFormattedTime(System.currentTimeMillis() - it.value) }
}
