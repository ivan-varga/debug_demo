package com.five.demo.debug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.five.demo.debug.congrats.Congrats
import com.five.demo.debug.email.EmailSubmission
import com.five.demo.debug.main.MainViewModel
import com.five.demo.debug.offices.Offices
import com.five.demo.debug.onboarding.OnBoarding
import com.five.demo.debug.terms.Terms
import com.five.demo.debug.ui.theme.DebugTheme
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebugTheme {
                val navController = rememberNavController()
                val hasAgreedToTerms = runBlocking {
                    mainViewModel.hasAgreed()
                }
                NavHost(navController = navController, startDestination = if (!hasAgreedToTerms) Terms.route else OnBoarding.route) {

                    composable(Terms.route) {
                        Terms.Terms(onAgree = { navController.navigate(OnBoarding.route) })
                    }
                    composable(OnBoarding.route) {
                        OnBoarding.OnBoarding(onDoneClick = { navController.navigate(Offices.route) })
                    }

                    composable(Offices.route) {
                        Offices.Offices { navController.navigate(EmailSubmission.Route) }
                    }

                    composable(EmailSubmission.Route) { EmailSubmission.EmailSubmission { navController.navigate(Congrats.Route) } }
                    composable(Congrats.Route) { Congrats.Congrats() }
                }
            }
        }
    }
}
