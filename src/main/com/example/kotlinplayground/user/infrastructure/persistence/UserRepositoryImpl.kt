package com.example.kotlinplayground.user.infrastructure.persistence

import com.example.kotlinplayground.user.domain.*
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val jpaUserRepository: JpaUserRepository) : UserRepository {

    override fun findById(id: UserId): User? {
        return jpaUserRepository.findById(id.value).orElse(null)?.toDomain()
    }

    override fun findByEmail(email: Email): User? {
        return jpaUserRepository.findByEmail(email.value)?.toDomain()
    }

    override fun findAll(): List<User> {
        return jpaUserRepository.findAll().map { it.toDomain() }
    }

    override fun save(user: User): User {
        val entity = if (user.id != null) {
            val existingEntity = jpaUserRepository.findById(user.id.value).orElse(null)
            if (existingEntity != null) {
                existingEntity.updateFrom(user)
                existingEntity
            } else {
                UserEntity.from(user)
            }
        } else {
            UserEntity.from(user)
        }
        
        val savedEntity = jpaUserRepository.save(entity)
        val savedUser = savedEntity.toDomain()
        
        return if (user.id == null) {
            user.assignId(UserId(savedEntity.id!!))
        } else {
            savedUser
        }
    }

    override fun deleteById(id: UserId) {
        jpaUserRepository.deleteById(id.value)
    }

    override fun existsByEmail(email: Email): Boolean {
        return jpaUserRepository.existsByEmail(email.value)
    }
}