package com.five.demo.debug.offices

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.TimeUrgency
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OfficesViewModel(
    private val remainingFormattedTime: RemainingFormattedTime,
    private val timeUrgency: TimeUrgency
) : ViewModel() {

    fun remainingTime(): Flow<String> = remainingFormattedTime()

    fun urgency(): Flow<Urgency> = timeUrgency()
}
