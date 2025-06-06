package com.example.kotlinplayground.user.domain

interface UserRepository {
    fun findById(id: UserId): User?
    fun findByEmail(email: Email): User?
    fun findAll(): List<User>
    fun save(user: User): User
    fun deleteById(id: UserId)
    fun existsByEmail(email: Email): Boolean
}