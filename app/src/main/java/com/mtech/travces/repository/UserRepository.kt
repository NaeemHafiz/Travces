package com.mtech.travces.repository

import android.annotation.SuppressLint
import android.app.Application
import com.mtech.travces.data.remote.travces.TravcesRetroFitClientInstance
import com.mtech.travces.data.remote.travces.UserDataSource
import com.mtech.travces.data.remote.travces.model.params.*
import com.mtech.travces.utils.ErrorUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(var context: Application) {


    fun getApiService() = TravcesRetroFitClientInstance.getInstance(context)!!.getService()

    @SuppressLint("CheckResult")
    fun login(phone: String, password: String, callback: UserDataSource.LoginCallback) {
        val params = LoginParams()
        params.phone = phone
        params.password = password

        getApiService().login(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onLoginResponse(it.data) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }

    @SuppressLint("CheckResult")
    fun sendCoordinates(params: PusherParams, callback: UserDataSource.sendCoordinatesCallback) {
        getApiService().sendCoordinates(params.latitude, params.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onsendCoodinatesResponse(it.message)
            }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })

        getApiService().sendCoordinates(params.latitude, params.longitude)

    }

    @SuppressLint("CheckResult")
    fun register(params: RegisterParams, callback: UserDataSource.RegisterCallback) {
        getApiService().register(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onRegisterResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })

    }

    @SuppressLint("CheckResult")
    fun updateChildProfile(
        params: UpdateChildParams,
        callback: UserDataSource.updateChildCallback
    ) {
        getApiService().updateChildProfile(
            params.fname,
            params.lname,
            params.pickup_location,
            params.drop_location,
            params.pickup_time,
            params.drop_time,
            params.institute_name,
            params.child_id
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onupdateChildResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })

    }

    @SuppressLint("CheckResult")
    fun updateProfile(
        userId: String,
        params: UpdateProfileParams,
        callback: UserDataSource.UpdateProfileCallback
    ) {
        getApiService().updateProfile(
            userId,
            params.fname,
            params.lname,
            params.email,
            params.phone,
            params.cnic,
            params.address,
            params.password,
            params.password_confirmation
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onUpdateResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }

    @SuppressLint("CheckResult")
    fun getChildrenList(parentId: String, callback: UserDataSource.getChildrenListCallback) {
        getApiService().getChildrenList(parentId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.ongetChildListResponse(it) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }


    @SuppressLint("CheckResult")
    fun addChild(params: ChildParams, callback: UserDataSource.addChildCallback) {
        getApiService().addChild(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onaddChildResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }

    @SuppressLint("CheckResult")
    fun forgotPassword(email: String, callback: UserDataSource.ForgotPasswordCallback) {
        getApiService().forgotPassword(email).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onForgotPasswordResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }

    @SuppressLint("CheckResult")
    fun getDriverList(parentId: String, callback: UserDataSource.getDriverListCallback) {
        getApiService().getDriverList(parentId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.ongetDriverListResponse(it) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })
    }
}
