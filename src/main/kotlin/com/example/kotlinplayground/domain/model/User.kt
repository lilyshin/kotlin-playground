package com.example.kotlinplayground.domain.model

import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val email: String,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun updateName(newName: String): User {
        return this.copy(name = newName, updatedAt = LocalDateTime.now())
    }
}