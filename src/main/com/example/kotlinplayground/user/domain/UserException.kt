package com.example.kotlinplayground.user.domain

sealed class UserException(message: String) : RuntimeException(message)

class UserNotFoundException(userId: Long) : UserException("User with id $userId not found")

class UserAlreadyExistsException(email: String) : UserException("User with email $email already exists")