package com.five.demo.debug.offices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val question: String,
    val answers: List<Int>,
    @SerialName("correct_answer_index")
    val correctAnswerIndex: String,
)
