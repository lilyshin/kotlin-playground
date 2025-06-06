package com.example.kotlinplayground.user.domain

import java.time.LocalDateTime

class User private constructor(
    val id: UserId?,
    val email: Email,
    val name: UserName,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    private val domainEvents: MutableList<UserEvent> = mutableListOf()
) {
    companion object {
        fun create(email: Email, name: UserName): User {
            val user = User(
                id = null,
                email = email,
                name = name,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
            user.addDomainEvent(UserCreatedEvent(user))
            return user
        }
        
        fun of(id: UserId, email: Email, name: UserName, createdAt: LocalDateTime, updatedAt: LocalDateTime): User {
            return User(id, email, name, createdAt, updatedAt)
        }
    }
    
    fun updateName(newName: UserName): User {
        require(newName != this.name) { "새로운 이름이 기존 이름과 동일합니다" }
        
        val updatedUser = User(
            id = this.id,
            email = this.email,
            name = newName,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now()
        )
        updatedUser.addDomainEvent(UserUpdatedEvent(updatedUser))
        return updatedUser
    }
    
    fun assignId(id: UserId): User {
        return User(
            id = id,
            email = this.email,
            name = this.name,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            domainEvents = this.domainEvents
        )
    }
    
    private fun addDomainEvent(event: UserEvent) {
        domainEvents.add(event)
    }
    
    fun getDomainEvents(): List<UserEvent> = domainEvents.toList()
    
    fun clearDomainEvents() {
        domainEvents.clear()
    }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }
    
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}