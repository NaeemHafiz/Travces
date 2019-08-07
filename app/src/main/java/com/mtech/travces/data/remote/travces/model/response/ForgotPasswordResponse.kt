package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.ForgotPasswordData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class ForgotPasswordResponse(
    val data: ForgotPasswordData
) : BaseResponse(), Serializable