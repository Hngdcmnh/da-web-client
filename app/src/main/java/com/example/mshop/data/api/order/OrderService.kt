package com.example.mshop.data.api.order

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.Timeout
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.dto.order.UpsertOrderDto
import com.example.mshop.data.dto.order.OrderDto
import com.example.mshop.data.dto.order.UpsertOrderPromotionDto
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.order.OrderCartProduct
import com.example.mshop.domain.entity.order.OrderStatusCartProduct
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL)
@EnableAuthentication(Constants.Key.TOKEN)
@Timeout(read = 20000, write = 20000)
@LoggingLever(level = Level.BODY)
interface OrderService {

    @POST("orders")
    suspend fun createOrder(@Body upsertOrderDto: UpsertOrderDto): Response<com.example.mshop.data.api.BaseResponse<OrderDto>>

    @GET("orders/{orderId}")
    suspend fun getOrderById(@Path("orderId") orderId: String): Response<com.example.mshop.data.api.BaseResponse<OrderDto>>

    @PATCH("orders/{orderId}/promotion")
    suspend fun updatePromotion(
        @Path("orderId") orderId: String,
        @Body upsertOrderPromotionDto: UpsertOrderPromotionDto
    ): Response<com.example.mshop.data.api.BaseResponse<UpsertOrderPromotionDto>>

    @DELETE("orders/{orderId}/cancel")
    suspend fun cancelOrderById(@Path("orderId") orderId: String): Response<BaseResponse<*>>

    @GET("orders/order-supplier/users")
    suspend fun getOrdersByStatus(@Query("status") status :String):Response<BaseResponse<List<OrderStatusCartProduct>>>

    @GET("orders/order-supplier/user")
    suspend fun getOrdersByUserId():Response<BaseResponse<List<OrderDto>>>
}