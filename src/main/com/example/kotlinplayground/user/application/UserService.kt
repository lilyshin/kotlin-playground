package com.example.kotlinplayground.user.application

import com.example.kotlinplayground.user.application.dto.CreateUserRequest
import com.example.kotlinplayground.user.application.dto.UpdateUserRequest
import com.example.kotlinplayground.user.application.dto.UserResponse
import com.example.kotlinplayground.user.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userDomainService: UserDomainService
) {
    
    @Transactional(readOnly = true)
    fun findById(id: Long): UserResponse {
        val userId = UserId(id)
        val user = userRepository.findById(userId) 
            ?: throw UserNotFoundException(id)
        return UserResponse.from(user)
    }
    
    @Transactional(readOnly = true)
    fun findAll(): List<UserResponse> {
        return userRepository.findAll()
            .map { UserResponse.from(it) }
    }
    
    fun create(request: CreateUserRequest): UserResponse {
        val email = Email(request.email)
        val name = UserName(request.name)
        
        userDomainService.checkEmailDuplication(email)
        
        val user = User.create(email, name)
        val savedUser = userRepository.save(user)
        return UserResponse.from(savedUser)
    }
    
    fun update(id: Long, request: UpdateUserRequest): UserResponse {
        val userId = UserId(id)
        val user = userRepository.findById(userId) 
            ?: throw UserNotFoundException(id)
            
        val newName = UserName(request.name)
        val updatedUser = user.updateName(newName)
        val savedUser = userRepository.save(updatedUser)
        return UserResponse.from(savedUser)
    }
    
    fun delete(id: Long) {
        val userId = UserId(id)
        if (userRepository.findById(userId) == null) {
            throw UserNotFoundException(id)
        }
        userRepository.deleteById(userId)
    }
}