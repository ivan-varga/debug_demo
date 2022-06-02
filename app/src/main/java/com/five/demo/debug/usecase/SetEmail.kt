package com.five.demo.debug.usecase

import com.five.demo.debug.email.EmailSource
import org.koin.core.annotation.Singleton

@Singleton
class SetEmail(private val emailSource: EmailSource) {

    suspend operator fun invoke(email: String) = emailSource.setEmail(email)
}
