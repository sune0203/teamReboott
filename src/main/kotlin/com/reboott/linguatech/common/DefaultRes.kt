package com.reboott.linguatech.common

data class DefaultRes<T>(
    val statusCode: Int,
    val responseMessage: String,
    val data: T? = null
) {
    companion object {
        fun <T> res(statusCode: Int, responseMessage: String, data: T? = null): DefaultRes<T> {
            return DefaultRes(statusCode, responseMessage, data)
        }
    }
}
