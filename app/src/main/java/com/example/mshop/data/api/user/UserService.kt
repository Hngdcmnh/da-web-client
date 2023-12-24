package com.example.mshop.data.api.user

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.dto.user.AddressSuggestionDto
import com.example.mshop.data.dto.user.UserDto
import com.example.mshop.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL)
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface UserService {
    @GET("users/")
    suspend fun getUser(): Response<com.example.mshop.data.api.BaseResponse<UserDto>>

    @PUT("users/")
    suspend fun updateUser(@Body userDto: UserDto): Response<com.example.mshop.data.api.BaseResponse<UserDto>>

    @GET("addresses/suggestion/province")
    suspend fun getAddressSuggestionProvince(): Response<com.example.mshop.data.api.BaseResponse<List<AddressSuggestionDto>>>

    @GET("addresses/suggestion/district/{provinceId}")
    suspend fun getAddressSuggestionDistrict(
        @Path("provinceId") provinceId: Int
    ): Response<com.example.mshop.data.api.BaseResponse<List<AddressSuggestionDto>>>

    @GET("addresses/suggestion/ward/{districtId}")
    suspend fun getAddressSuggestionWard(
        @Path("districtId") districtId: Int
    ): Response<com.example.mshop.data.api.BaseResponse<List<AddressSuggestionDto>>>
}