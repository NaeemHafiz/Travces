package com.mtech.travces.data.remote.travces.model.response

import com.mtech.travces.data.remote.travces.model.data.UpdateProfileData
import com.mtech.travces.data.remote.travces.model.response.base.BaseResponse
import java.io.Serializable

class UpdateProfileResponse (
    val data: UpdateProfileData
    ) : BaseResponse(), Serializable
