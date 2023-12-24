package com.example.mshop.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.example.mshop.data.api.user.UserService
import com.example.mshop.data.ktx.data
import com.example.mshop.data.ktx.errorBody
import com.example.mshop.data.mapper.toAddressSuggestion
import com.example.mshop.data.mapper.toUser
import com.example.mshop.data.mapper.toUserDto
import com.example.mshop.domain.entity.user.AddressSuggestion
import com.example.mshop.domain.entity.user.User
import com.example.mshop.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun getUserInfo(): DataState<User, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(userService.getUser())
        if (response.isSuccess) {
            response.data().toUser()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun updateUser(user: User, isUpdate: Boolean): DataState<User, Exception> = stateOn(ioDispatcher){
        val userDto = user.toUserDto()
        val response = handleResponse(userService.updateUser(userDto))
        if (response.isSuccess) {
            response.data().toUser()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getAddressSuggestionProvince(): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(userService.getAddressSuggestionProvince())
        if (response.isSuccess) {
            response.data().map { it.toAddressSuggestion() }
        } else {
            throw response.error().errorBody()
        }

    }

    override suspend fun getAddressSuggestionDistrict(provinceId: Int): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(userService.getAddressSuggestionDistrict(provinceId))

        if (response.isSuccess) {
            response.data().map { it.toAddressSuggestion() }
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun getAddressSuggestionWard(districtId: Int): DataState<List<AddressSuggestion>, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(userService.getAddressSuggestionWard(districtId))

        if (response.isSuccess) {
            response.data().map { it.toAddressSuggestion() }
        } else {
            throw response.error().errorBody()
        }
    }

}