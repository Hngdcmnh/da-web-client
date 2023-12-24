package com.example.mshop.data.dto.discovery

data class ProductListDto(
    val products: List<ProductInfoDto>,
    val pagination: PaginationDto,
)
