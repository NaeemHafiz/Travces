package com.mtech.travces.repository

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.mtech.travces.data.remote.travces.TravcesRetroFitClientInstance
import com.mtech.travces.data.remote.travces.UserDataSource
import com.mtech.travces.data.remote.travces.model.data.RegisterData
import com.mtech.travces.data.remote.travces.model.params.LoginParams
import com.mtech.travces.data.remote.travces.model.params.RegistrationParams
import com.mtech.travces.utils.ErrorUtils
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(var context: Application) {

    private fun getApiService() = TravcesRetroFitClientInstance.getInstance(context)!!.getService()

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
    fun register(params: RegistrationParams, callback: UserDataSource.RegisterCallback) {
        getApiService().register(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.onRegisterResponse(it.message) }
                , {
                    callback.onPayloadError(ErrorUtils.parseError(it))
                })

    }


}