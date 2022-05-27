package com.five.demo.debug.congrats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.five.demo.debug.ui.theme.DebugTheme
import org.koin.androidx.compose.get

object Congrats {
    const val Route = "congrats"

    @Composable
    fun Congrats(congratsViewModel: CongratsViewModel = get()) {
        val email: State<String> = congratsViewModel.email().collectAsState(initial = "")
        val time: State<String> = congratsViewModel.finalTime().collectAsState(initial = "X")
        val exactTime: State<String> = congratsViewModel.exactTime().collectAsState(initial = "0:00")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Congrats! You've won ${time.value}m of massage!",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = email.value,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            )
            Text(
                "Your time\n${exactTime.value}",
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
fun CongratsPreview() {
    DebugTheme {
        Congrats.Congrats()
    }
}
