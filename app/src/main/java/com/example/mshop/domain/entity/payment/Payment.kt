package com.example.mshop.domain.entity.payment

data class Payment(
    val paymentId: String = "",
    val paymentUrl: String? = null,
    val paymentType: PaymentType,
    val bankCode: String? = null,
    val orderId: String,
    val orderType: String = "200000",
    val paymentStatus: PaymentStatus,
    val amount: Double
)