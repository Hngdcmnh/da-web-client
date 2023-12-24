package com.example.mshop.data.dto.order

import com.example.mshop.data.dto.payment.PaymentDto
import com.example.mshop.data.dto.user.UserDto
import com.example.mshop.domain.entity.cart.CartProduct
import com.example.mshop.domain.entity.order.OrderCartProduct
import com.sudo248.base_android.base.ItemDiff
import java.time.LocalDateTime

data class OrderDto(
    val orderId: String,
    val cartId: String,
    val payment: PaymentDto? = null,
    val user: UserDto,
    val status:String,
    val createdAt:LocalDateTime,
    val totalPrice: Double,
    val totalPromotionPrice: Double,
    val totalShipmentPrice: Double,
    val finalPrice: Double,
    val address: String,
    val cartProducts: List<OrderCartProduct>
): ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        val order = other as OrderDto
        return orderId == order.orderId
    }

}