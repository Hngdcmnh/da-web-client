package com.example.mshop.data.mapper

import com.example.mshop.data.dto.auth.TokenDto
import com.example.mshop.domain.entity.auth.Token

fun TokenDto.toToken(): Token {
    return Token(
        token = this.token,
        refreshToken = this.refreshToken
    )
}