package com.example.kotlinplayground.user.application.dto

import com.example.kotlinplayground.user.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class CreateUserRequest(
    @field:Email(message = "올바른 이메일 형식이 아닙니다")
    @field:NotBlank(message = "이메일은 필수입니다")
    val email: String,
    
    @field:NotBlank(message = "이름은 필수입니다")
    val name: String
)

data class UpdateUserRequest(
    @field:NotBlank(message = "이름은 필수입니다")
    val name: String
)

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id!!.value,
                email = user.email.value,
                name = user.name.value,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}