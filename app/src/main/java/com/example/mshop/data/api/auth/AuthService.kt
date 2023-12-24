package com.example.mshop.data.api.auth

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.api.auth.request.AccountRequest
import com.example.mshop.data.api.auth.request.ChangePasswordRequest
import com.example.mshop.data.api.auth.request.OtpRequest
import com.example.mshop.data.dto.auth.TokenDto
import com.example.mshop.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path




@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL + "auth/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface AuthService {

    @GET("refresh-token")
    suspend fun refreshToken(@Header("refresh-token") refreshToken: String): Response<com.example.mshop.data.api.BaseResponse<TokenDto>>

    @POST("sign-in")
    suspend fun signIn(@Body accountRequest: AccountRequest): Response<com.example.mshop.data.api.BaseResponse<TokenDto>>

    @POST("sign-up")
    suspend fun signUp(@Body accountRequest: AccountRequest): Response<com.example.mshop.data.api.BaseResponse<String>>

    @POST("change-password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Response<com.example.mshop.data.api.BaseResponse<String>>

    @POST("generate-otp/{phoneNumber}")
    suspend fun generateOtp(@Path("phoneNumber") phoneNumber: String): Response<com.example.mshop.data.api.BaseResponse<String>>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body otp: OtpRequest): Response<com.example.mshop.data.api.BaseResponse<TokenDto>>

    @GET("logout")
    suspend fun logout(): Response<com.example.mshop.data.api.BaseResponse<String>>
}