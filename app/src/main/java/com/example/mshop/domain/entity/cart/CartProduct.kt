package com.example.mshop.domain.entity.cart

import com.sudo248.base_android.base.ItemDiff
import com.example.mshop.data.dto.cart.ProductInfoDto

data class CartProduct(
    val cartProductId: String = "",
    val cartId: String = "",
    var quantity: Int = 0,
    val totalPrice: Double = 0.0,
    val product: ProductInfoDto? = null
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        val cartProduct = other as CartProduct
        return cartProduct.product?.productId == product?.productId &&
                cartProduct.quantity == quantity
    }

}