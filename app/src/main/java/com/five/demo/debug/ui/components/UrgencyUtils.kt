package com.five.demo.debug.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.five.demo.debug.timer.Urgency

@Composable
fun Urgency.toColor(): Color =
    when (this) {
        Urgency.LOW -> MaterialTheme.colorScheme.tertiary
        Urgency.MEDIUM -> MaterialTheme.colorScheme.surfaceVariant
        Urgency.HIGH -> MaterialTheme.colorScheme.errorContainer
    }
