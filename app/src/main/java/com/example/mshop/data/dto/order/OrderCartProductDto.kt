package com.example.mshop.data.dto.order

data class OrderCartProductDto(
    val cartProductId: String,
    val cartId: String,
    val quantity: Int,
    val totalPrice: Double,
    val product: OrderProductInfoDto
)