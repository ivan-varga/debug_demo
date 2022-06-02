package com.five.demo.debug

import android.app.Application
import com.five.demo.debug.congrats.CongratsModule
import com.five.demo.debug.email.EmailModule
import com.five.demo.debug.main.MainModule
import com.five.demo.debug.offices.OfficesModule
import com.five.demo.debug.onboarding.OnBoardingModule
import com.five.demo.debug.preferences.PreferencesModule
import com.five.demo.debug.terms.TermsModule
import com.five.demo.debug.timer.TimerModule
import com.five.demo.debug.usecase.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class DebugApplication : Application() {

    init {
        startKoin {
            androidContext(this@DebugApplication)

            modules(
                PreferencesModule().module,
                OnBoardingModule().module,
                TimerModule().module,
                EmailModule().module,
                UseCaseModule().module,
                CongratsModule().module,
                OfficesModule().module,
                MainModule().module,
                TermsModule().module,
            )
        }
    }
}
