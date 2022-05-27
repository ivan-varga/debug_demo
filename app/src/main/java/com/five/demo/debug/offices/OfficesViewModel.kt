package com.five.demo.debug.offices

import androidx.lifecycle.ViewModel
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.usecase.RemainingFormattedTime
import com.five.demo.debug.usecase.TimeUrgency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.android.annotation.KoinViewModel

const val deserializationJson = """
{
    "question": "How many Endava offices are there in Europe?",
    "answers": [
    "23",
    "12",
    "55",
    "8"
    ],
    "correct_answer_index": 2
}
"""

@KoinViewModel
class OfficesViewModel(
    private val remainingFormattedTime: RemainingFormattedTime,
    private val timeUrgency: TimeUrgency
) : ViewModel() {

    private val selectedAnswerState = MutableStateFlow(SelectedAnswer.NONE)

    fun showQuestion() =
        runCatching { Json.decodeFromString<Question>(deserializationJson) }
            .onFailure { throw it }
            .isSuccess

    fun remainingTime(): Flow<String> = remainingFormattedTime()

    fun urgency(): Flow<Urgency> = timeUrgency()

    fun selectAnswer(selectedAnswer: SelectedAnswer) {
        selectedAnswerState.value = selectedAnswer
    }

    fun selectedAnswer(): Flow<SelectedAnswer> = selectedAnswerState
}

enum class SelectedAnswer {
    ANSWER_1, ANSWER_2, ANSWER_3, ANSWER_4, NONE
}
