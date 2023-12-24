package com.example.mshop.data.dto.auth

data class TokenDto(
    val token: String,
    val refreshToken: String? = null,
    val userId: String? = null,
)

