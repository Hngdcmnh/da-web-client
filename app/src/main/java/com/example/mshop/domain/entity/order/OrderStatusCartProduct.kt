package com.example.mshop.domain.entity.order

import com.sudo248.base_android.base.ItemDiff
import java.time.LocalDateTime

class OrderStatusCartProduct(
    val orderId:String,
    val cartProductId: String,
    val cartId: String,
    val quantity: Int,
    val createdAt: LocalDateTime,
    val totalPrice: Double,
    val product: OrderProductInfo
): ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return other is OrderCartProduct && other == this
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return other is OrderCartProduct && other.cartProductId == cartProductId
    }
}