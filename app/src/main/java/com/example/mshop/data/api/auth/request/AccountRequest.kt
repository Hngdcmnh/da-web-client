package com.example.mshop.data.api.auth.request

import com.google.gson.annotations.SerializedName
import com.example.mshop.domain.entity.auth.Provider

data class AccountRequest(
    @SerializedName("emailOrPhoneNumber")
    val phoneNumber: String,
    val password: String,
    val provider: Provider? = null
)