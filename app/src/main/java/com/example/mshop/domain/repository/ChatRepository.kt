package com.example.mshop.domain.repository

import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.chat.Chat
import com.example.mshop.domain.entity.chat.Conversation

interface ChatRepository {
    suspend fun getConversation(supplierId: String): DataState<Conversation, Exception>
    suspend fun sendMessageToSupplier(supplierId: String, content: String): DataState<Chat, Exception>
}