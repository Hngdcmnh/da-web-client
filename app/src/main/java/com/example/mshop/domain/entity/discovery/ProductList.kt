package com.example.mshop.domain.entity.discovery

data class ProductList(
    val products: List<ProductInfo>,
    val pagination: Pagination,
)
