package com.five.demo.debug.terms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.five.demo.debug.R
import com.five.demo.debug.ui.theme.DebugTheme
import org.koin.androidx.compose.get

object Terms {
    const val route = "terms"

    @Composable
    fun Terms(
        onAgree: () -> Unit = {},
        viewModel: TermsViewModel = get()
    ) {
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

                val scrollState = rememberScrollState(0)
                Text(
                    text = stringResource(R.string.terms_and_conditions),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 42.dp)
                        .horizontalScroll(scrollState),
                    fontSize = 12.sp
                )

                Button(
                    onClick = {
                        viewModel.agree()
                        onAgree()
                    },
                    contentPadding = PaddingValues(horizontal = 48.dp, vertical = 14.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.terms_button),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    DebugTheme {
        Terms.Terms()
    }
}
