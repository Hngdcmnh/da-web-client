package com.example.mshop.ui.activity.main.fragment.review

import android.net.Uri
import com.example.mshop.domain.entity.discovery.UpsertReview

interface ViewController {
    fun toast(message: String)
    fun getPathImageFromUri(uri: Uri): String
    fun getUpsertReview(): UpsertReview
}