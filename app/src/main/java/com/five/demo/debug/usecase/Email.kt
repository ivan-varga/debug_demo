package com.five.demo.debug.usecase

import com.five.demo.debug.email.EmailSource
import org.koin.core.annotation.Singleton

@Singleton
class Email(private val emailSource: EmailSource) {

    operator fun invoke() = emailSource.email()
}
