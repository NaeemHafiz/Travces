package com.mtech.travces.utils

import com.mtech.travces.data.remote.base.ApiErrorResponse
import org.json.JSONObject

object ErrorUtils {

    fun parseError(json: String): ApiErrorResponse {
        return try {
            val json = JSONObject(json)
            val error = ApiErrorResponse(
                json.optInt("code", 0),
                json.optString("message", ""),
                json.optString("name", "")
            )
            error
        } catch (ex: Exception) {
            ApiErrorResponse(0, "", "")
        }
    }

    fun parseError(t: Throwable): ApiErrorResponse {
        return try {
            ApiErrorResponse(0, t.message!!, t.message!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
            ApiErrorResponse(0, "", "")
        }
    }
}