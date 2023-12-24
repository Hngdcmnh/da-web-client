package com.example.mshop.domain.repository

import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.cart.AddCartProduct
import com.example.mshop.domain.entity.cart.AddCartProducts
import com.example.mshop.domain.entity.cart.Cart

interface CartRepository {
    suspend fun getCart(): DataState<Cart, Exception>
    suspend fun countItemInActiveCart(): DataState<Int, Exception>


    //--------
    suspend fun addProductToActiveCart(upsertCartProduct: AddCartProduct): DataState<Cart,Exception>
    suspend fun deleteProductInActiveCart(cartId:String,productId:String): DataState<Cart,Exception>
    suspend fun getActiveCart(): DataState<Cart,Exception>

    suspend fun createProcessingCart(addCartProducts: AddCartProducts): DataState<Cart,Exception>
}