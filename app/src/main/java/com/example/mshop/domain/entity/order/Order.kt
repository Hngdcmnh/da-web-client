package com.example.mshop.domain.entity.order

import com.example.mshop.domain.entity.payment.Payment
import com.example.mshop.domain.entity.user.User

data class Order(
    val orderId: String,
    val cartId: String,
    val payment: Payment? = null,
    val user: User,
    val totalPrice: Double,
    val totalPromotionPrice: Double,
    val totalShipmentPrice: Double,
    val finalPrice: Double,
    val address: String,
    val cartProducts: List<OrderCartProduct>
)
