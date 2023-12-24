package com.example.mshop.domain.repository

import android.content.Context
import android.net.Uri
import com.sudo248.base_android.core.DataState

interface ImageRepository {
    suspend fun uploadImage(context: Context, uri: Uri): DataState<String, Exception>
    suspend fun uploadImage(pathImage: String): DataState<String, Exception>
}