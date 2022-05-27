package com.five.demo.debug.offices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.five.demo.debug.R
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.ui.components.toColor
import com.five.demo.debug.ui.theme.DebugTheme
import org.koin.androidx.compose.get

object Offices {
    const val route = "offices"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Offices(
        officesViewModel: OfficesViewModel = get(),
        onCorrectAnswerSelected: () -> Unit = {}
    ) {
        val time = officesViewModel.remainingTime().collectAsState(initial = "7:00")
        val urgency = officesViewModel.urgency().collectAsState(initial = Urgency.LOW)
        val shouldShowQuestion = officesViewModel.showQuestion()
        val selectedAnswer: State<SelectedAnswer> = officesViewModel.selectedAnswer().collectAsState(initial = SelectedAnswer.NONE)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .alpha(if (shouldShowQuestion) 1f else 0f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.offices),
                    contentDescription = stringResource(id = R.string.endava_offices),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(id = R.string.endava_question),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)
                        .align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedAnswer.value == SelectedAnswer.ANSWER_1,
                        onClick = { officesViewModel.selectAnswer(SelectedAnswer.ANSWER_1) }
                    )
                    Text(
                        text = stringResource(id = R.string.endava_answer1),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    RadioButton(
                        selected = selectedAnswer.value == SelectedAnswer.ANSWER_2,
                        onClick = { officesViewModel.selectAnswer(SelectedAnswer.ANSWER_2) },
                        modifier = Modifier.padding(start = 64.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.endava_answer2),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedAnswer.value == SelectedAnswer.ANSWER_3,
                        onClick = {
                            officesViewModel.selectAnswer(SelectedAnswer.ANSWER_3)
                            onCorrectAnswerSelected()
                        }
                    )
                    Text(
                        text = stringResource(id = R.string.endava_answer3),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    RadioButton(
                        selected = selectedAnswer.value == SelectedAnswer.ANSWER_4,
                        onClick = { officesViewModel.selectAnswer(SelectedAnswer.ANSWER_4) },
                        modifier = Modifier.padding(start = 64.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.endava_answer4),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }



            Text(
                text = time.value,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(urgency.value.toColor())
                    .padding(vertical = 8.dp)
                    .navigationBarsPadding(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
private fun DefaultPreview() {
    DebugTheme {
        Offices.Offices()
    }
}
