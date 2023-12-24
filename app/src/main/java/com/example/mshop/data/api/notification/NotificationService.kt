package com.example.mshop.data.api.notification

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.notification.Notification
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL)
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface NotificationService {

    @POST("notification/token/{token}")
    suspend fun saveToken(@Path("token") token: String): Response<com.example.mshop.data.api.BaseResponse<Boolean>>

    @POST("notification/send/promotion")
    suspend fun sendNotificationPromotion(@Body notification: Notification): Response<com.example.mshop.data.api.BaseResponse<String>>
}