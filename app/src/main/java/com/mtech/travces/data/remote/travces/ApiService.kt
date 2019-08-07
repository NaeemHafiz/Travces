package com.mtech.travces.data.remote.travces

import com.mtech.travces.data.remote.travces.model.params.LoginParams
import com.mtech.travces.data.remote.travces.model.params.RegistrationParams
import com.mtech.travces.data.remote.travces.model.response.LoginResponse
import com.mtech.travces.data.remote.travces.model.response.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body body: LoginParams): Observable<LoginResponse>

    @POST("register")
    fun register(@Body body: RegistrationParams): Observable<RegisterResponse>

}
