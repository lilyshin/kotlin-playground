package com.example.kotlinplayground.infrastructure.persistence.repository

import com.example.kotlinplayground.domain.model.User
import com.example.kotlinplayground.domain.repository.UserRepository
import com.example.kotlinplayground.infrastructure.persistence.entity.UserEntity
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val jpaUserRepository: JpaUserRepository) : UserRepository {

    override fun findById(id: Long): User? {
        return jpaUserRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findByEmail(email: String): User? {
        return jpaUserRepository.findByEmail(email)?.toDomain()
    }

    override fun findAll(): List<User> {
        return jpaUserRepository.findAll().map { it.toDomain() }
    }

    override fun save(user: User): User {
        val entity = UserEntity.from(user)
        val savedEntity = jpaUserRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun deleteById(id: Long) {
        jpaUserRepository.deleteById(id)
    }

    override fun existsByEmail(email: String): Boolean {
        return jpaUserRepository.existsByEmail(email)
    }
}
