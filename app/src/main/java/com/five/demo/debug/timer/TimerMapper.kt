package com.five.demo.debug.timer

import org.koin.core.annotation.Singleton

private const val DEFAULT_TOTAL_TIME = 7 * 60 * 1000L

enum class Urgency {
    LOW,
    MEDIUM,
    HIGH
}

interface TimerMapper {

    fun toFormattedTime(timeElapsed: Long, totalTime: Long = DEFAULT_TOTAL_TIME): String
    fun toUrgencyByTimeElapsed(timeElapsed: Long, totalTime: Long = DEFAULT_TOTAL_TIME): Urgency
}

@Singleton
class TimerMapperImpl : TimerMapper {
    override fun toFormattedTime(timeElapsed: Long, totalTime: Long): String {
        val diff = totalTime - timeElapsed
        val seconds = (diff / 1000).takeIf { it >= 0 } ?: 0
        val minutes = (seconds / 60).takeIf { it >= 0 } ?: 0

        val presentableSeconds = seconds % 60

        return "$minutes:${if (presentableSeconds < 10) "0$presentableSeconds" else "$presentableSeconds"}"
    }

    override fun toUrgencyByTimeElapsed(timeElapsed: Long, totalTime: Long): Urgency {
        val diff = totalTime - timeElapsed
        val seconds = (diff / 1000).takeIf { it >= 0 } ?: 0
        val minutes = (seconds / 60).takeIf { it >= 0 } ?: 0

        return when {
            minutes > 4 -> Urgency.LOW
            minutes in 2..4 -> Urgency.MEDIUM
            else -> Urgency.HIGH
        }
    }
}
