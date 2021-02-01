package com.tinuade.learningapp.data.remote

import com.tinuade.learningapp.utils.Resources
import retrofit2.Response

abstract class LearningDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resources<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resources.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resources<T> {
        return Resources.error("Network call has failed for a following reason: $message")
    }

}