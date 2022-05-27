package com.five.demo.debug.email

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.annotation.Singleton

interface EmailSource {

    fun email(): Flow<String>
    fun setEmail(email: String)
}

@Singleton
class EmailSourceImpl : EmailSource {
    private val emailState = MutableStateFlow("")
    override fun email(): Flow<String> = emailState

    override fun setEmail(email: String) {
        emailState.value = email
    }

}
