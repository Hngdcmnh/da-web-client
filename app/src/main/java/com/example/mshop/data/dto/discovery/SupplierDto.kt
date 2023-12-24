package com.example.mshop.data.dto.discovery

import com.example.mshop.data.dto.user.AddressDto
import java.time.LocalDateTime

data class SupplierDto(
    val supplierId: String,
    val name: String,
    val avatar: String,
    val brand: String,
    val contactUrl: String,
    val totalProducts: Int,
    val rate: Float,
    val createAt: LocalDateTime,
    val address: AddressDto,
)