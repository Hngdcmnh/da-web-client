package com.example.mshop.domain.entity.order

import com.sudo248.base_android.base.ItemDiff

data class OrderCartProduct(
    val cartProductId: String,
    val cartId: String,
    val quantity: Int,
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