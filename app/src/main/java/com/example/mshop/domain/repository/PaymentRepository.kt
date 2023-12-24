package com.example.mshop.domain.repository

import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.payment.Payment

interface PaymentRepository {
    suspend fun pay(payment: Payment): DataState<Payment, Exception>
}