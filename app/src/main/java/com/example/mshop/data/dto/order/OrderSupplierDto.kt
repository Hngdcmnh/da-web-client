package com.example.mshop.data.dto.order

import com.example.mshop.data.dto.discovery.SupplierInfoDto
import com.example.mshop.domain.entity.order.OrderStatus
import com.example.mshop.domain.entity.order.Shipment
import java.time.LocalDateTime

data class OrderSupplierDto(
    val orderSupplierId: String,
    val supplier: SupplierInfoDto,
    val status: OrderStatus,
    val shipment: Shipment,
    val totalPrice: Double,
    val expectedReceiveDateTime: LocalDateTime,
    val orderCartProducts: List<OrderCartProductDto>
)
