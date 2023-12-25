package com.example.mshop.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import com.facebook.FacebookSdk
import com.sudo248.base_android.app.BaseApplication
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.example.mshop.domain.common.Constants
import dagger.hilt.android.HiltAndroidApp




@HiltAndroidApp
class MainApplication : BaseApplication() {
    override val enableNetworkObserver: Boolean = true
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtils.createApplicationSharePreference(this)
        FacebookSdk.setApplicationId("6161985857254099")
        setupNotification()
    }

    private fun setupNotification() {

        val important = NotificationManager.IMPORTANCE_DEFAULT

        val promotionChannel = NotificationChannel(
            Constants.Notification.PROMOTION_NOTIFICATION_CHANNEL_ID,
            Constants.Notification.PROMOTION_NOTIFICATION_CHANNEL_NAME,
            important
        )
        promotionChannel.description = "This notification alert when merchant add new or update a promotion"
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(promotionChannel)

        val messageChannel = NotificationChannel(
            Constants.Notification.MESSAGE_NOTIFICATION_CHANNEL_ID,
            Constants.Notification.MESSAGE_NOTIFICATION_CHANNEL_NAME,
            important
        )
        messageChannel.description = "This notification alert when new message"
        manager.createNotificationChannel(messageChannel)
    }
}