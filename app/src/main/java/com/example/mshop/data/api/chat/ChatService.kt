package com.example.mshop.data.api.chat

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.dto.chat.ChatDto
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.chat.Chat
import com.example.mshop.domain.entity.chat.Conversation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL + "chat/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface ChatService {

    @GET("conversation/{topic}")
    suspend fun getConversation(@Path("topic") topic: String): Response<com.example.mshop.data.api.BaseResponse<Conversation>>

    @POST("conversation/{topic}/send")
    suspend fun sendMessageToTopic(@Path("topic") topic: String,@Body chatDto: ChatDto): Response<com.example.mshop.data.api.BaseResponse<Chat>>
}