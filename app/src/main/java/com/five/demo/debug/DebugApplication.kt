package com.five.demo.debug

import android.app.Application
import com.five.demo.debug.onboarding.OnBoardingModule
import com.five.demo.debug.preferences.PreferencesModule
import com.five.demo.debug.timer.TimerModule
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
                TimerModule().module
            )
        }
    }
}
