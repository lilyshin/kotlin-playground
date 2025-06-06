package com.example.kotlinplayground.domain.exception

sealed class DomainException(message: String) : RuntimeException(message)

class UserNotFoundException(userId: Long) : DomainException("User with id $userId not found")

class UserAlreadyExistsException(email: String) : DomainException("User with email $email already exists")

class InvalidEmailFormatException(email: String) : DomainException("Invalid email format: $email")