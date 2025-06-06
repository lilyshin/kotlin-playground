package com.example.kotlinplayground.user.domain

@JvmInline
value class UserId(val value: Long) {
    init {
        require(value > 0) { "사용자 ID는 양수여야 합니다" }
    }
}