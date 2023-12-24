package com.example.mshop.data.dto.cart

data class CartDto(
    val userId: String = "",
    val cartId: String = "",
    var totalPrice: Double = 0.0,
    var quantity: Int = 0,
    val status: String = "",
    var cartProducts: List<CartProductDto> = listOf()
)







