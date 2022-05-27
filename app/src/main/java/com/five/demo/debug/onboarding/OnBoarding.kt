package com.five.demo.debug.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import com.five.demo.debug.R
import com.five.demo.debug.timer.Urgency
import com.five.demo.debug.ui.components.toColor
import com.five.demo.debug.ui.theme.DebugTheme
import org.koin.androidx.compose.get

object OnBoarding {
    const val route = "onboarding"

    fun NavGraphBuilder.registerDestination() {

    }

    @Composable
    fun OnBoarding(
        onBoardingViewModel: OnBoardingViewModel = get(),
        onDoneClick: () -> Unit = {}
    ) {

        val timeRemaining = onBoardingViewModel.remainingTimeFormatted().collectAsState(initial = "7:00")
        val urgencyColor = onBoardingViewModel.urgency().collectAsState(initial = Urgency.LOW)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_five_logo),
                    contentDescription = "FIVE an Endava company logo",
                    modifier = Modifier
                        .padding(
                            start = 104.dp,
                            top = 90.dp,
                            end = 104.dp,
                        )
                        .fillMaxWidth(),
                )

                Text(
                    text = stringResource(R.string.onboarding_description),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 42.dp),
                    fontSize = 2.sp
                )

                Button(
                    onClick = { onDoneClick() },
                    contentPadding = PaddingValues(horizontal = 48.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.onboarding_button),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = timeRemaining.value,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(urgencyColor.value.toColor())
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
        OnBoarding.OnBoarding()
    }
}
