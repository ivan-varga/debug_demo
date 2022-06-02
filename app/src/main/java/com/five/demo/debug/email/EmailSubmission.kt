package com.five.demo.debug.email

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.ui.components.toColor
import com.five.demo.debug.ui.theme.DebugTheme
import org.koin.androidx.compose.get

object EmailSubmission {
    const val Route = "email"

    @Composable
    fun EmailSubmission(
        emailViewModel: EmailViewModel = get(),
        onSubmit: () -> Unit = {}
    ) {
        val focusManager = LocalFocusManager.current

        val timerRemainingState = emailViewModel.remainingTimeFormatted().collectAsState(initial = "")
        val email = emailViewModel.email().collectAsState(initial = "")
        val urgencyColor = emailViewModel.urgency().collectAsState(initial = Urgency.LOW)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Submit your email",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { emailViewModel.onEmailChanged(it) },
                    shape = RectangleShape,
                    singleLine = true,
                    placeholder = { Text(text = "example@email.com") },
                    keyboardOptions = KeyboardOptions.Companion.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    })
                )
                Button(onClick = { }) { // Do nothing?
                    Text(
                        text = "Submit",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = timerRemainingState.value,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(urgencyColor.value.toColor())
                    .padding(vertical = 8.dp)
                    .navigationBarsPadding()
                    .align(Alignment.BottomCenter),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun EmailSubmissionPreview() {
    DebugTheme {
        EmailSubmission.EmailSubmission()
    }
}
