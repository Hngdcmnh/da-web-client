package com.example.mshop.ui.activity.main;

import android.net.Uri

interface ViewController {
    fun pickImage()
    fun takeImage(uri: Uri)

    fun createTempPictureUri(): Uri
}
