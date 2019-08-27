package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class GetChildrenResponse(
    val Data: List<GetChildrenData>?
) : BaseResponse(), Serializable