package com.example.mshop.ui.ktx

import com.denzcoskun.imageslider.models.SlideModel
import com.example.mshop.BuildConfig

fun String.toImageUrl(): String {
    if (!startsWith("http")) return "${com.example.mshop.BuildConfig.BASE_URL}storage/images/$this"
    return this
}

fun String.toSlideModel(): SlideModel {
    return SlideModel(imageUrl = this.toImageUrl())
}