package com.five.demo.debug.congrats

import androidx.lifecycle.ViewModel
import com.five.demo.debug.email.EmailSource
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.StopTimer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CongratsViewModel(
    private val remainingFormattedTime: RemainingFormattedTime,
    private val emailSource: EmailSource,
    private val stopTimer: StopTimer,
) : ViewModel() {
    private val bgScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        bgScope.launch {
            stopTimer()
        }
    }

    fun timeWon(): Flow<String> = remainingFormattedTime().map {
        val timeTokens = it.split(":")

        if (timeTokens.all { it.toInt() == 0 }) timeTokens[0]
        else (timeTokens[0].toInt() + 1).coerceAtMost(5).toString()
    }

    fun exactTime(): Flow<String> = remainingFormattedTime()

    fun email(): Flow<String> = emailSource.email()

    override fun onCleared() {
        bgScope.cancel()

        super.onCleared()
    }
}
