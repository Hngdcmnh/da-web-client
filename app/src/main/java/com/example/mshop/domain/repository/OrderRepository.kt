package com.example.mshop.domain.repository

import com.example.mshop.data.dto.order.OrderDto
import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.order.Order
import com.example.mshop.domain.entity.order.OrderCartProduct
import com.example.mshop.domain.entity.order.OrderStatusCartProduct
import com.example.mshop.domain.entity.order.UpsertOrderPromotion

interface OrderRepository {
    suspend fun createOrder(cartId: String): DataState<Order, Exception>
    suspend fun getOrderById(orderId: String): DataState<Order, Exception>
    suspend fun updatePromotion(
        orderId: String,
        upsertOrderPromotion: UpsertOrderPromotion
    ): DataState<UpsertOrderPromotion, Exception>

    suspend fun cancelOrderById(orderId: String): DataState<Boolean, Exception>
    suspend fun getOrderByStatus(status: String): DataState<List<OrderStatusCartProduct>, Exception>
    suspend fun getOrderByUserId(): DataState<List<OrderDto>, Exception>

}