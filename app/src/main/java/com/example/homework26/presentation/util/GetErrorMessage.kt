package com.example.homework26.presentation.util

import com.example.homework26.data.common.ErrorType

fun getErrorMessage(errorType: ErrorType): String {
    return when (errorType) {
        ErrorType.SocketTimeout -> "The server did not respond in time."
        ErrorType.UnknownHost -> "The server could not be found."
        ErrorType.SSLHandshake -> "There was an error establishing a secure connection."
        ErrorType.Http -> "There was an error with the HTTP request."
        ErrorType.IO -> "There was an input/output error."
        is ErrorType.UnknownError -> errorType.message
    }
}