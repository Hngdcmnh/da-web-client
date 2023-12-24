package com.example.mshop.domain.entity.cart

import com.example.mshop.domain.entity.discovery.Product
import com.example.mshop.domain.entity.discovery.Supplier

data class SupplierProductDetail(
    val supplier: Supplier,
    val product: Product,
    val amountLeft: Int,
    val price: Double,
    val soldAmount: Double,
    val rate: Double,
)
