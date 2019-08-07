package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.RegisterData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class RegisterResponse(
    val data: RegisterData
) : BaseResponse(), Serializable