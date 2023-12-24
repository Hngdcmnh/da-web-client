package com.example.mshop.data.di

import com.example.mshop.data.repository.*
import com.example.mshop.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindDiscoveryRepository(discoveryRepository: DiscoveryRepositoryImpl): DiscoveryRepository

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindPaymentRepository(paymentRepository: PaymentRepositoryImpl): PaymentRepository

    @Binds
    abstract fun bindImageRepository(imageRepository: ImageRepositoryImpl): ImageRepository

    @Binds
    abstract fun bindCartRepository(cartRepository: CartRepositoryImpl): CartRepository

    @Binds
    abstract fun bindOrderRepository(orderRepository: OrderRepositoryImpl): OrderRepository

    @Binds
    abstract fun bindChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository

}