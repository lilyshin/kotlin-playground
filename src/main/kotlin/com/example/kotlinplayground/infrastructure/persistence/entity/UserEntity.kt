package com.example.kotlinplayground.infrastructure.persistence.entity

import com.example.kotlinplayground.domain.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(nullable = false, unique = true)
    val email: String,
    
    @Column(nullable = false)
    var name: String,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toDomain(): User {
        return User(
            id = this.id,
            email = this.email,
            name = this.name,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
    
    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email,
                name = user.name,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}