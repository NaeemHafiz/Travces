package com.mtech.travces.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mtech.travces.data.remote.base.ApiErrorResponse
import com.mtech.travces.data.remote.travces.UserDataSource
import com.mtech.travces.data.remote.travces.model.data.LoginData
import com.mtech.travces.data.remote.travces.model.data.RegisterData
import com.mtech.travces.data.remote.travces.model.params.RegistrationParams
import com.mtech.travces.data.remote.travces.model.response.RegisterResponse
import com.mtech.travces.repository.UserRepository
import com.mtech.travces.utils.extensions.*

class UserViewModel(context: Application) : BaseAndroidViewModel(context) {

    var loginResponse: MutableLiveData<OneShotEvent<LoginData>> = MutableLiveData()
    var registerResponse: MutableLiveData<OneShotEvent<String>> = MutableLiveData()

    var userRepository: UserRepository = UserRepository(context)

    fun login(phone: String, password: String) {
        showProgressBar(true)
        var canProceed = true
        if (phone.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_PHONE_FIELD, "Enter Phone Number")
            canProceed = false
        }
        if (password.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_CONFIRM_PASSWORD, "Enter Password")
            canProceed = false
        }

        if (!canProceed) return
        userRepository.login(phone, password, object : UserDataSource.LoginCallback {
            override fun onLoginResponse(data: LoginData) {
                showProgressBar(false)
                loginResponse.value = OneShotEvent(data)
            }

            override fun onPayloadError(error: ApiErrorResponse) {
                showProgressBar(false)
                showSnackbarMessage(error.message)
            }
        })
    }

    fun register(params: RegistrationParams) {
        showProgressBar(true)
        var canProceed = true
        if (params.phone.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_PHONE_FIELD, "Enter Phone Number")
            canProceed = false
        }
        if (params.password.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_CONFIRM_PASSWORD, "Enter Password")
            canProceed = false
        }
        if (params.email.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_EMAIL_FIELD, "Enter Email")
            canProceed = false
        }
        if (params.fname.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_FIRST_NAME_FIELD, "Enter First Name")
            canProceed = false
        }
        if (params.lname.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_LAST_NAME_FIELD, "Enter Last Name")
            canProceed = false
        }
        if (params.password_confirmation.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_CONFIRM_PASSWORD, "Enter Confirm Password")
            canProceed = false
        }
        if (params.address.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_ADDRESS, "Enter Address")
            canProceed = false
        }
        if (params.cnic.isEmpty()) {
            handleErrorType(ERROR_CODE_EMPTY_CNIC, "Enter CNIC Number")
            canProceed = false
        }
        if (!params.password_confirmation.equals(params.password)) {
            handleErrorType(ERROR_CODE_EMPTY_CNIC, "Passwords Does Not Match")
            canProceed = false
        }
        if (params.password.length < 8) {
            handleErrorType(ERROR_CODE_EMPTY_CNIC, "Password Must be greater than or equal to 8 characters")
            canProceed = false
        }
        if (params.password_confirmation.length < 8) {
            handleErrorType(ERROR_CODE_EMPTY_CNIC, "Password Must be greater than or equal to 8 characters")
            canProceed = false
        }
        if (!isEmailValid(params.email)) {
            handleErrorType(ERROR_CODE_EMPTY_CNIC, "invalid Email")
            canProceed = false

        }

        if (!canProceed) return

        userRepository.register(params, object : UserDataSource.RegisterCallback {
            override fun onRegisterResponse(message: String) {
                showProgressBar(false)
                registerResponse.value = OneShotEvent(message)
            }

            override fun onPayloadError(error: ApiErrorResponse) {
                showProgressBar(false)
                showSnackbarMessage(error.message)
            }
        })
    }
}