package com.example.mshop.data.ktx

import com.sudo248.base_android.exception.ApiException
import com.example.mshop.data.api.BaseResponse


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 07:45 - 06/03/2023
 */

fun ApiException.Companion.fromResponse(response: com.example.mshop.data.api.BaseResponse<*>): ApiException {
    return ApiException(
        statusCode = response.statusCode,
        message = response.message,
        data = response.data
    )
}

fun ApiException.errorBody(): ApiException {
    return if (data != null && data is com.example.mshop.data.api.BaseResponse<*>) {
        val errorBody = data as com.example.mshop.data.api.BaseResponse<*>
        ApiException.fromResponse(errorBody)
    } else {
        this
    }
}