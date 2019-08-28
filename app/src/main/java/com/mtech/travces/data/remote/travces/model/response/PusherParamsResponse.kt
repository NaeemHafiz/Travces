package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.PusherData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class PusherParamsResponse(
    val data: PusherData
) : BaseResponse(), Serializable