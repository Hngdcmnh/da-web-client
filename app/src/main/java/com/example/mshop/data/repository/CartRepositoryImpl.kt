package com.example.mshop.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.example.mshop.data.api.cart.CartService
import com.example.mshop.data.ktx.data
import com.example.mshop.data.ktx.errorBody
import com.example.mshop.data.mapper.toAddSupplierProductDto
import com.example.mshop.data.mapper.toCart
import com.example.mshop.domain.entity.cart.AddCartProduct
import com.example.mshop.domain.entity.cart.AddCartProducts
import com.example.mshop.domain.entity.cart.Cart
import com.example.mshop.domain.entity.cart.toAddCartProductsDto
import com.example.mshop.domain.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService,
    private val ioDispatcher: CoroutineDispatcher
) : CartRepository {

    override suspend fun getCart(): DataState<Cart, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(cartService.getActiveCart())
        if (response.isSuccess) {
            response.data().toCart()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun countItemInActiveCart(): DataState<Int, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(cartService.countItemInActiveCart())
        if (response.isSuccess) {
            response.data()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun addProductToActiveCart(upsertCartProduct: AddCartProduct): DataState<Cart, Exception> =
        stateOn(ioDispatcher) {
            val response =
                handleResponse(cartService.updateProductToActiveCart(upsertCartProduct.toAddSupplierProductDto()))
            if (response.isSuccess) {
                response.data().toCart()
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun deleteProductInActiveCart(
        cartId: String,
        productId: String
    ): DataState<Cart, Exception> = stateOn(ioDispatcher) {
        val response =
            handleResponse(cartService.deleteProductInActiveCart(cartId = cartId, productId = productId))
        if (response.isSuccess) {
            response.data().toCart()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getActiveCart(): DataState<Cart, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(cartService.getActiveCart())
        if (response.isSuccess) {
            response.data().toCart()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun createProcessingCart(addCartProducts: AddCartProducts): DataState<Cart, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(cartService.createProcessingCartWithProduct(addCartProducts.toAddCartProductsDto()))
        if (response.isSuccess) {
            response.data().toCart()
        } else {
            throw response.error().errorBody()
        }
    }
}