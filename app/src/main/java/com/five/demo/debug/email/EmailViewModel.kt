package com.five.demo.debug.email

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.usecase.Email
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.SetEmail
import com.five.demo.debug.usecase.TimeUrgency
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EmailViewModel(
    private val remainingFormattedTime: RemainingFormattedTime,
    private val setEmail: SetEmail,
    private val queryEmail: Email,
    private val timeUrgency: TimeUrgency,
) : ViewModel() {

    private val bgScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun remainingTimeFormatted(): Flow<String> = remainingFormattedTime()

    fun onEmailChanged(email: String) {
        bgScope.launch {
            setEmail(email)
            setEmail(email = email)
        }
    }

    fun email(): Flow<String> = queryEmail()

    fun urgency(): Flow<Urgency> = timeUrgency()

    override fun onCleared() {
        bgScope.cancel()

        super.onCleared()
    }
}
