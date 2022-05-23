package com.five.demo.debug.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@ComponentScan
class PreferencesModule {

    @Single
    fun dataStore(androidContext: Context) = androidContext.dataStore
}

