package com.example.kotlinplayground.user.domain

import org.springframework.stereotype.Service

@Service
class UserDomainService(
    private val userRepository: UserRepository
) {
    fun checkEmailDuplication(email: Email) {
        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException(email.value)
        }
    }
    
    fun isEmailUnique(email: Email): Boolean {
        return !userRepository.existsByEmail(email)
    }
}