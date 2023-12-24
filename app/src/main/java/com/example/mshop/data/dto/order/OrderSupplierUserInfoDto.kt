package com.example.mshop.data.dto.order

import com.example.mshop.domain.entity.order.OrderStatus
import java.time.LocalDateTime


data class OrderSupplierUserInfoDto(
    private val orderSupplierId: String,
    private val supplierId: String,
    private val supplierName: String,
    private val supplierAvatar: String,
    private val supplierBrand: String,
    private val supplierContactUrl: String,
    private val status: OrderStatus,
    private val expectedReceiveDateTime: LocalDateTime,
    private val totalPrice: Double = 0.0,
    private val orderCartProducts: List<OrderCartProductDto>,
)