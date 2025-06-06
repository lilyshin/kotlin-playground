package com.example.kotlinplayground.application.service

import com.example.kotlinplayground.application.dto.CreateUserRequest
import com.example.kotlinplayground.application.dto.UpdateUserRequest
import com.example.kotlinplayground.application.dto.UserResponse
import com.example.kotlinplayground.domain.exception.UserAlreadyExistsException
import com.example.kotlinplayground.domain.exception.UserNotFoundException
import com.example.kotlinplayground.domain.model.User
import com.example.kotlinplayground.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    
    @Transactional(readOnly = true)
    fun findById(id: Long): UserResponse {
        val user = userRepository.findById(id) 
            ?: throw UserNotFoundException(id)
        return UserResponse.from(user)
    }
    
    @Transactional(readOnly = true)
    fun findAll(): List<UserResponse> {
        return userRepository.findAll()
            .map { UserResponse.from(it) }
    }
    
    fun create(request: CreateUserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw UserAlreadyExistsException(request.email)
        }
        
        val user = User(
            email = request.email,
            name = request.name
        )
        
        val savedUser = userRepository.save(user)
        return UserResponse.from(savedUser)
    }
    
    fun update(id: Long, request: UpdateUserRequest): UserResponse {
        val user = userRepository.findById(id) 
            ?: throw UserNotFoundException(id)
            
        val updatedUser = user.updateName(request.name)
        val savedUser = userRepository.save(updatedUser)
        return UserResponse.from(savedUser)
    }
    
    fun delete(id: Long) {
        if (userRepository.findById(id) == null) {
            throw UserNotFoundException(id)
        }
        userRepository.deleteById(id)
    }
}