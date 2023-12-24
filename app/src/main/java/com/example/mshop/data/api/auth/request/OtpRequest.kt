package com.example.mshop.data.api.auth.request

import com.google.gson.annotations.SerializedName



data class OtpRequest(
    @SerializedName("emailOrPhoneNumber")
    val phoneNumber: String,
    val otp: String,
)
