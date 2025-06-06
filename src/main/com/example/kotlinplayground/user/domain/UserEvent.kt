package com.example.kotlinplayground.user.domain

import java.time.LocalDateTime

sealed class UserEvent(
    val occurredAt: LocalDateTime = LocalDateTime.now()
)

data class UserCreatedEvent(
    val user: User
) : UserEvent()

data class UserUpdatedEvent(
    val user: User
) : UserEvent()