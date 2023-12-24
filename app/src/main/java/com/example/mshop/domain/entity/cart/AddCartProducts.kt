package com.example.mshop.domain.entity.cart

import com.example.mshop.data.dto.cart.CartProductsDto
import com.example.mshop.data.mapper.toCartProductDto

data class AddCartProducts(
    val cartProducts: List<CartProduct>
)

fun AddCartProducts.toAddCartProductsDto(): CartProductsDto {
    return CartProductsDto(
        cartProducts = this.cartProducts.map { it.toCartProductDto()}
    )
}