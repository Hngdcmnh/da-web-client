package com.example.mshop.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.example.mshop.data.api.chat.ChatService
import com.example.mshop.data.dto.chat.ChatDto
import com.example.mshop.data.ktx.data
import com.example.mshop.data.ktx.errorBody
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.chat.Chat
import com.example.mshop.domain.entity.chat.Conversation
import com.example.mshop.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
    private val ioDispatcher: CoroutineDispatcher,
) : ChatRepository {
    override suspend fun getConversation(supplierId: String): DataState<Conversation, Exception> =
        stateOn(ioDispatcher) {
            val response = handleResponse(
                chatService.getConversation(
                    "chat%$supplierId%${
                        SharedPreferenceUtils.getString(
                            Constants.Key.USER_ID
                        )
                    }"
                )
            )
            if (response.isSuccess) {
                response.data()
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun sendMessageToSupplier(
        supplierId: String,
        content: String
    ): DataState<Chat, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(
            chatService.sendMessageToTopic(
                "chat%$supplierId%${SharedPreferenceUtils.getString(Constants.Key.USER_ID)}",
                ChatDto(
                    supplierId,
                    content
                )
            )
        )
        if (response.isSuccess) {
            response.data()
        } else {
            throw response.error().errorBody()
        }
    }
}