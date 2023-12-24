package com.example.mshop.data.api


open class BaseResponse<Data> {
    var statusCode: Int = 0
    var success: Boolean = false
    var message: String = ""
    var data: Data? = null

    override fun toString(): String {
        return "{ statusCode: $statusCode\n" +
                "success: $success\n" +
                "message: $message\n" +
                "data: $data" +
                "}"
    }
}