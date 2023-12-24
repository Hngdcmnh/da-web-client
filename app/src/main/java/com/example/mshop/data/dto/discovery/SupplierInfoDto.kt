package com.example.mshop.data.dto.discovery

import com.example.mshop.data.dto.user.AddressDto

data class SupplierInfoDto(
    val supplierId: String,
    val name: String,
    val avatar: String,
    val contactUrl: String,
    val rate: Float,
    val address: AddressDto
)
