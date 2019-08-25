package com.mtech.travces.data.remote.travces

import com.mtech.travces.data.remote.base.ApiErrorResponse
import com.mtech.travces.data.remote.travces.model.data.GetDriverData
import com.mtech.travces.data.remote.travces.model.data.LoginData
import com.mtech.travces.data.remote.travces.model.response.GetDriverResponse


interface UserDataSource {
    interface LoginCallback {
        fun onLoginResponse(data: LoginData)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface RegisterCallback {
        fun onRegisterResponse(message: String)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface ForgotPasswordCallback {
        fun onForgotPasswordResponse(message: String)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface UpdateProfileCallback {
        fun onUpdateResponse(message: String)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface addChildCallback {
        fun onaddChildResponse(message: String)
        fun onPayloadError(error: ApiErrorResponse)
    }

    interface getDriverListCallback {
        fun ongetDriverListResponse(data: GetDriverResponse)
        fun onPayloadError(error: ApiErrorResponse)
    }


}