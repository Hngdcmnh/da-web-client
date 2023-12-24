package com.example.mshop.data.api.auth.request



data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
)