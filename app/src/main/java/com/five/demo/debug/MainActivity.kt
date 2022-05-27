package com.five.demo.debug

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.five.demo.debug.congrats.Congrats
import com.five.demo.debug.email.EmailSubmission
import com.five.demo.debug.offices.Offices
import com.five.demo.debug.onboarding.OnBoarding
import com.five.demo.debug.ui.theme.DebugTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebugTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = OnBoarding.route) {
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
