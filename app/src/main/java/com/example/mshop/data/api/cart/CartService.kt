package com.example.mshop.data.api.cart

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.dto.cart.AddCartProductDto
import com.example.mshop.data.dto.cart.CartDto
import com.example.mshop.data.dto.cart.CartProductsDto
import com.example.mshop.domain.common.Constants
import retrofit2.Response
import retrofit2.http.*

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL + "carts/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface CartService {

    @GET("active/count-item")
    suspend fun countItemInActiveCart(): Response<com.example.mshop.data.api.BaseResponse<Int>>

    //------------------------------------
    @GET("active")
    suspend fun getActiveCart():Response<com.example.mshop.data.api.BaseResponse<CartDto>>

    @GET("/api/v1/carts/")
    suspend fun getCartByStatus():Response<com.example.mshop.data.api.BaseResponse<CartDto>>

    @POST("product")
    suspend fun updateProductToActiveCart(@Body upsertCartProductDto: AddCartProductDto):Response<com.example.mshop.data.api.BaseResponse<CartDto>>

    @DELETE("product")
    suspend fun deleteProductInActiveCart(
        @Query("cartId") cartId: String,
        @Query("productId") productId: String
    ): Response<com.example.mshop.data.api.BaseResponse<CartDto>>

    @POST("processing")
    suspend fun createProcessingCartWithProduct(@Body cartProductsDto: CartProductsDto):Response<com.example.mshop.data.api.BaseResponse<CartDto>>
}