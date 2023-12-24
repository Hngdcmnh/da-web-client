package com.example.mshop.domain.entity.discovery

import com.example.mshop.domain.entity.user.Address

data class SupplierInfo(
    val supplierId: String,
    val name: String,
    val avatar: String,
    val contactUrl: String,
    val rate: Float,
    val address: Address
)
