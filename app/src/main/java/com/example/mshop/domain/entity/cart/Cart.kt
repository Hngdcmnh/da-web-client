package com.example.mshop.domain.entity.cart


data class Cart(
    val cartId: String,
    val totalPrice: Double,
    val quantity: Int,
    val status: String,
    val cartProducts: List<CartProduct>
)
