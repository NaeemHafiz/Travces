package com.mtech.travces.utils

import android.util.Log
import com.google.gson.JsonParser
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
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
        var message = "API ERROR RESPONSE"
        if (t is HttpException) {
            val errorJsonString = t.response()
                .errorBody()?.string()
            message = JsonParser().parse(errorJsonString)
                .asJsonObject["message"]
                .asString
        } else {
            message = t.message?:message
        }

        return try {
            ApiErrorResponse(0, message!!, "")
        } catch (ex: Exception) {
            ex.printStackTrace()
            ApiErrorResponse(0, message!!, "")
        }
    }
}