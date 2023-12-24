package com.example.mshop.ui.ktx

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.mshop.BuildConfig
import java.io.File

fun Context.createTempPictureUri(
    provider: String = "${com.example.mshop.BuildConfig.APPLICATION_ID}.provider",
    fileName: String = "picture_${System.currentTimeMillis()}",
    fileExtension: String = ".png"
): Uri {
    val tempFile = File.createTempFile(
        fileName, fileExtension, cacheDir
    ).apply {
        createNewFile()
    }

    return FileProvider.getUriForFile(applicationContext, provider, tempFile)
}