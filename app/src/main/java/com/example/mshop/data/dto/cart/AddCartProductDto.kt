package com.example.mshop.data.dto.cart

data class AddCartProductDto(
    val cartProductId: String,
    val productId: String,
    val quantity: Int
)
