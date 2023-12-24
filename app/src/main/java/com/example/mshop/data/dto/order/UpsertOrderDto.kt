package com.example.mshop.data.dto.order

import com.example.mshop.domain.entity.order.OrderStatus

data class UpsertOrderDto(
    val orderId: String? = null,
    val cartId: String,
    val paymentId: String? = null,
    val promotionId: String? = null,
    val status: OrderStatus? = null,
)