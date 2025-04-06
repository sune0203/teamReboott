package com.reboott.linguatech.common

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private fun isSwaggerRequest(request: HttpServletRequest): Boolean {
        val uri = request.requestURI
        return uri.startsWith("/v3/api-docs") || uri.contains("swagger") || uri.endsWith(".yaml")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException, request: HttpServletRequest): ResponseEntity<DefaultRes<String>> {
        if (isSwaggerRequest(request)) throw ex

        return ResponseEntity
                .status(StatusCode.BAD_REQUEST)
                .body(
                        DefaultRes.res(
                                statusCode = StatusCode.BAD_REQUEST,
                                responseMessage = ex.message ?: ResponseMessage.BAD_REQUEST
                        )
                )
    }

    @ExceptionHandler(Exception::class)
    fun handleServerError(ex: Exception, request: HttpServletRequest): ResponseEntity<DefaultRes<String>> {
        if (isSwaggerRequest(request)) throw ex

        return ResponseEntity
                .status(StatusCode.INTERNAL_SERVER_ERROR)
                .body(
                        DefaultRes.res(
                                statusCode = StatusCode.INTERNAL_SERVER_ERROR,
                                responseMessage = ex.message ?: ResponseMessage.INTERNAL_SERVER_ERROR
                        )
                )
    }
}
