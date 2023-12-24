package com.example.mshop.data.dto.cart

import com.example.mshop.data.dto.discovery.ProductDto
import com.example.mshop.data.dto.discovery.SupplierDto

data class SupplierProductDetailDto(
    val supplier: SupplierDto,
    val product: ProductDto,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Double,
    val rate: Double,
)