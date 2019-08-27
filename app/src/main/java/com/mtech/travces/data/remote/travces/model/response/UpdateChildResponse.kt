package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.UpdateChildData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class UpdateChildResponse (
    val data: UpdateChildData
) : BaseResponse(), Serializable