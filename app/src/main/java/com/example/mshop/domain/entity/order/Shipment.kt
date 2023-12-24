package com.example.mshop.domain.entity.order

import java.time.LocalDateTime

data class Shipment(
    val shipmentType: ShipmentType = ShipmentType.STANDARD,
    val deliveryTime: Int = 0,
    val shipmentPrice: Double = 0.0,
    val receivedDateTime: LocalDateTime? = null,
)
