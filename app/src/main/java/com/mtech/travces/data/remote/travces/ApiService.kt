package com.mtech.travces.data.remote.travces

import com.mtech.travces.data.remote.travces.model.params.*
import com.mtech.travces.data.remote.travces.model.response.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body body: LoginParams): Observable<LoginResponse>

    @POST("register")
    fun register(@Body body: RegisterParams): Observable<RegisterResponse>


    @FormUrlEncoded
    @PUT("users/{userId}")
    fun updateProfile(
        @Path("userId") userId: String,
        @Field("fname") First_Name: String,
        @Field("lname") Last_Name: String,
        @Field("email") Email: String,
        @Field("phone") Phone: String,
        @Field("cnic") CNIC: String,
        @Field("address") Address: String,
        @Field("password") Password: String,
        @Field("password_confirmation") C_Password: String
    ): Observable<UpdateProfileResponse>

    @GET("users/{userId}")
    fun getProfile(@Body body: RegisterParams): Observable<RegisterResponse>

    @GET("notification/email")
    fun notificationApi(@Body body: RegisterParams): Observable<RegisterResponse>

    @GET("users/{userId}")
    fun getChildren(@Body body: RegisterParams): Observable<RegisterResponse>

    @FormUrlEncoded
    @POST("password/email")
    fun forgotPassword(@Field("email") email: String): Observable<ForgotPasswordResponse>

    @POST("children")
    fun addChild(@Body body: ChildParams): Observable<ChildResponse>

    @GET("children")
    fun getChildrenList(@Query("parent_id") parent_id: String): Observable<GetChildrenResponse>

    @GET("child_rides")
    fun getDriverList(@Query("parent_id") parent_id: String): Observable<GetDriverResponse>

    @FormUrlEncoded
    @POST("/simulate")
    fun sendCoordinates(@Field("latitude") latitude: String, @Field("longitude") longitude: String): Observable<PusherParamsResponse>

    @FormUrlEncoded
    @PUT("update_child")
    fun updateChildProfile(
        @Field("fname") fname: String,
        @Field("lname") lname: String,
        @Field("pickup_location") pickup_location: String,
        @Field("drop_location") drop_location: String,
        @Field("pickup_time") pickup_time: String,
        @Field("drop_time") drop_time: String,
        @Field("institute_name") institute_name: String,
        @Field("child_id") child_id: String
    ): Observable<UpdateChildResponse>
}
