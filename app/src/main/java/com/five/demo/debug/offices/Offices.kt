package com.five.demo.debug.offices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        onRadioButtonClick: () -> Unit = {}
    ) {
        val time = officesViewModel.remainingTime().collectAsState(initial = "7:00")
        val urgency = officesViewModel.urgency().collectAsState(initial = Urgency.LOW)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.offices),
                    contentDescription = "Endava Offices",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "How many offices does Endava have in Europe?",
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
                        selected = false,
                        onClick = { onRadioButtonClick() }
                    )
                    Text(
                        text = "23",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    RadioButton(
                        selected = false,
                        onClick = { onRadioButtonClick() },
                        modifier = Modifier.padding(start = 64.dp)
                    )
                    Text(
                        text = "12",
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
                        selected = false,
                        onClick = { onRadioButtonClick() }
                    )
                    Text(
                        text = "55",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    RadioButton(
                        selected = false,
                        onClick = { onRadioButtonClick() },
                        modifier = Modifier.padding(start = 64.dp)
                    )
                    Text(
                        text = "8",
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
