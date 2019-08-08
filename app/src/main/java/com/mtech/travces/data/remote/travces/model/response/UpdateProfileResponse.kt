package com.mtech.travces.data.remote.travces.model.response.base

import com.mtech.travces.data.remote.travces.model.data.UpdateProfileData
import java.io.Serializable

class UpdateProfileResponse (
    val data: UpdateProfileData
    ) : BaseResponse(), Serializable
