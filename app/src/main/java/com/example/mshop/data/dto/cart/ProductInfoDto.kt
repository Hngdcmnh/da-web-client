package com.example.mshop.data.dto.cart

data class ProductInfoDto(
    val productId: String = "",
    val sku: String = "",
    val name: String = "",
    val price: Float = 0.0f,
    val listedPrice: Float = 0.0f,
    val amount: Int = 0,
    val size:String = "",
    val discount: Int = 0,
    val brand: String = "",
    val rate: Float = 0.0f,
    val saleable: Boolean = true,
    val images: List<String>? = null,
)