package com.five.demo.debug.email

import com.five.demo.debug.preferences.EmailPreferences
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Singleton

interface EmailSource {

    fun email(): Flow<String>
    suspend fun setEmail(email: String)
}

@Singleton
class EmailSourceImpl(private val emailPreferences: EmailPreferences) : EmailSource {
    override fun email(): Flow<String> = emailPreferences.email()

    override suspend fun setEmail(email: String) = emailPreferences.setEmail(email)
}
