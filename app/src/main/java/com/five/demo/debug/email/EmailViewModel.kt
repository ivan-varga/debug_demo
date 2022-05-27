package com.five.demo.debug.email

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.TimeUrgency
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EmailViewModel(
    private val remainingFormattedTime: RemainingFormattedTime,
    private val emailSourceImpl: EmailSourceImpl,
    private val timeUrgency: TimeUrgency,
) : ViewModel() {

    fun remainingTimeFormatted(): Flow<String> = remainingFormattedTime()

    fun onEmailChanged(email: String) = emailSourceImpl.setEmail(email = email)

    fun email(): Flow<String> = emailSourceImpl.email()

    fun urgency(): Flow<Urgency> = timeUrgency()
}
