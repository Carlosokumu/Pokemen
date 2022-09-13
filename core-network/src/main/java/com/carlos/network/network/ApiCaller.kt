package com.carlos.network.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
):  ApiCallResult<T> = withContext(dispatcher) {
    try {
        ApiCallResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {

        when (throwable) {
            is IOException -> {
               ApiCallResult.ServerError(500)
            }
            is HttpException -> {
                ApiCallResult.ServerError(500)
            }
            else -> {
                ApiCallResult.ServerError(500)
            }
        }
    }
}