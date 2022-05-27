package com.five.demo.debug.onboarding

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.StartTimer
import com.five.demo.debug.usecase.TimeUrgency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OnBoardingViewModel(
    private val startTimer: StartTimer,
    private val remainingFormattedTime: RemainingFormattedTime,
    private val timeUrgency: TimeUrgency
) : ViewModel() {

    private val bgScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        bgScope.launch { startTimer() }
    }

    fun remainingTimeFormatted(): Flow<String> = remainingFormattedTime()

    fun urgency(): Flow<Urgency> = timeUrgency()
}
