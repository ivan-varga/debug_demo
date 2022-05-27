package com.five.demo.debug.congrats

import android.util.Log
import androidx.lifecycle.ViewModel
import com.five.demo.debug.email.EmailSource
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.StopTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.math.roundToInt

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

    fun finalTime(): Flow<String> = remainingFormattedTime().map {
        Log.d("asdfg", "finalTime")
        val timeTokens = it.split(":")
        (timeTokens[0].toInt() + (timeTokens[1].toFloat() / 60f).roundToInt()).toString()
    }

    fun exactTime(): Flow<String> = remainingFormattedTime().onEach {
        Log.d("asdfg", "exactTime")
    }

    fun email(): Flow<String> = emailSource.email().onEach {
        Log.d("asdfg", "email")
    }
}
