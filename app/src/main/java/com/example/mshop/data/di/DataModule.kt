package com.example.mshop.data.di

import com.google.gson.GsonBuilder
import com.sudo248.base_android.data.api.ApiService
import com.sudo248.base_android.data.api.api
import com.example.mshop.data.api.auth.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.mshop.data.api.cart.CartService
import com.example.mshop.data.api.chat.ChatService
import com.example.mshop.data.api.discovery.DiscoveryService
import com.example.mshop.data.api.image.ImageService
import com.example.mshop.data.api.order.OrderService
import com.example.mshop.data.api.notification.NotificationService
import com.example.mshop.data.api.payment.PaymentService
import com.example.mshop.data.api.user.UserService
import com.example.mshop.data.converter.LocalDateConverter
import com.example.mshop.data.converter.LocalDateTimeConverter
import java.time.LocalDate
import java.time.LocalDateTime




@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService = ApiService()

    @Singleton
    @Provides
    fun provideDiscoveryService(): DiscoveryService = api {
        converterFactory = GsonConverterFactory.create(
            GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter()).create()
        )
    }

    @Singleton
    @Provides
    fun provideUserService(): UserService = api {
        converterFactory = GsonConverterFactory.create(
            GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateConverter()).create()
        )
    }

    @Singleton
    @Provides
    fun provideImageService(): ImageService = ApiService()

    @Singleton
    @Provides
    fun providePaymentService(): PaymentService = ApiService()

    @Singleton
    @Provides
    fun provideCartService(): CartService = ApiService()


    @Singleton
    @Provides
    fun provideOrderService(): OrderService = api {
        converterFactory = GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateConverter())
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter())
                .create()
        )
    }

    @Singleton
    @Provides
    fun provideNotificationService(): NotificationService = ApiService()

    @Singleton
    @Provides
    fun provideChatService(): ChatService = ApiService()

    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO

}