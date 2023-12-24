package com.example.mshop.data.mapper

import com.example.mshop.data.dto.payment.PaymentDto
import com.example.mshop.domain.entity.payment.Payment
import com.example.mshop.domain.entity.payment.PaymentStatus
import com.example.mshop.domain.entity.payment.PaymentType

fun PaymentDto.toPayment(): Payment {
    return Payment(
        paymentId,
        paymentUrl,
        PaymentType.fromString(paymentType),
        bankCode,
        orderId,
        orderType,
        PaymentStatus.valueOf(paymentStatus),
        amount
    )
}