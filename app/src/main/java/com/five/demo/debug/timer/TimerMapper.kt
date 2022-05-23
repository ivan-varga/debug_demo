package com.five.demo.debug.timer

import org.koin.core.annotation.Singleton

private const val DEFAULT_TOTAL_TIME = 7 * 60 * 1000L

interface TimerMapper {

    fun toFormattedTime(timeElapsed: Long, totalTime: Long = DEFAULT_TOTAL_TIME): String
}

@Singleton
class TimerMapperImpl : TimerMapper {
    override fun toFormattedTime(timeElapsed: Long, totalTime: Long): String {
        val diff = totalTime - timeElapsed
        val seconds = (diff / 1000).takeIf { it >= 0 } ?: 0
        val minutes = (seconds / 60).takeIf { it >= 0 } ?: 0

        val presentableSeconds = seconds % 60

        return buildString {
            append(minutes)
            append(":")
            append(if (presentableSeconds < 10) "0$presentableSeconds" else "$presentableSeconds")
        }
    }

}
