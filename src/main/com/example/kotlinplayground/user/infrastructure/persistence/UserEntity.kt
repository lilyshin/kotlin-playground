package com.example.kotlinplayground.user.infrastructure.persistence

import com.example.kotlinplayground.user.domain.*
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
        return User.of(
            id = UserId(this.id!!),
            email = Email(this.email),
            name = UserName(this.name),
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
    
    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                id = user.id?.value,
                email = user.email.value,
                name = user.name.value,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
    
    fun updateFrom(user: User) {
        this.name = user.name.value
        this.updatedAt = user.updatedAt
    }
}