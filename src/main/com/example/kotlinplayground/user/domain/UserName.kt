package com.example.kotlinplayground.user.domain

@JvmInline
value class UserName(val value: String) {
    init {
        require(value.isNotBlank()) { "이름은 공백일 수 없습니다" }
        require(value.length >= 2) { "이름은 최소 2자 이상이어야 합니다" }
        require(value.length <= 50) { "이름은 최대 50자까지 가능합니다" }
        require(value.matches("^[가-힣a-zA-Z\\s]+$".toRegex())) { "이름은 한글, 영문, 공백만 포함할 수 있습니다" }
    }
}