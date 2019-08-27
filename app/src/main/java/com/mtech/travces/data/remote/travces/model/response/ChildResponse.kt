package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.DriverData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class ChildResponse(
    val data: DriverData
) : BaseResponse(), Serializable
