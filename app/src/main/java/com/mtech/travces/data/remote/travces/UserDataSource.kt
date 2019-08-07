package com.mtech.travces.data.remote.travces

import com.mtech.travces.data.remote.base.ApiErrorResponse
import com.mtech.travces.data.remote.travces.model.data.LoginData
import com.mtech.travces.data.remote.travces.model.response.RegisterResponse


interface UserDataSource {
    interface LoginCallback {
        fun onLoginResponse(data: LoginData)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface RegisterCallback {
        fun onRegisterResponse(data: RegisterResponse)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface ForgotPasswordCallback {
        fun onForgotPasswordResponse(message: String)
        fun onPayloadError(error: ApiErrorResponse)
    }


}