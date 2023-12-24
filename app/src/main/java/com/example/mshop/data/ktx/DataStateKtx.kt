package com.example.mshop.data.ktx

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.exception.ApiException
import com.example.mshop.data.api.BaseResponse

fun <Data> DataState<com.example.mshop.data.api.BaseResponse<Data>, ApiException>.data(): Data {
    if (isSuccess) {
        return get().data!!
    } else {
        throw error()
    }
}