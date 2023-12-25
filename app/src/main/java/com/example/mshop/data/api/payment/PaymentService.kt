package com.example.mshop.data.api.payment

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.api.payment.request.PaymentRequest
import com.example.mshop.data.dto.payment.PaymentDto
import com.example.mshop.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL + "payment/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface PaymentService {

    @POST("pay")
    suspend fun pay(@Body request: PaymentRequest): Response<BaseResponse<PaymentDto>>

}