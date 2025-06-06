package com.example.kotlinplayground.domain.repository

import com.example.kotlinplayground.domain.model.User

interface UserRepository {
    fun findById(id: Long): User?
    fun findByEmail(email: String): User?
    fun findAll(): List<User>
    fun save(user: User): User
    fun deleteById(id: Long)
    fun existsByEmail(email: String): Boolean
}