package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.utils.extensions.ERROR_CODE_EMPTY_CONFIRM_PASSWORD
import com.mtech.travces.utils.extensions.ERROR_CODE_EMPTY_PHONE_FIELD
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    lateinit var userViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        bLogin.setOnClickListener {
            userViewModel.login(etPhone.text.toString(), etPassword.text.toString())
        }

        tvForgotPassword.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_forgotPasswordFragment))
    }

    private fun attachViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        with(userViewModel) {
            snackbarMessage.observe(viewLifecycleOwner, Observer {
                val msg = it?.getContentIfNotHandled()
                if (!msg.isNullOrEmpty()) showToast(msg)
            })
            progressBar.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null)
                    showProgressDialog(show)
            })
            validationResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                showProgressDialog(false)
                if (show != null) {
                    when (show.errorCode) {
                        ERROR_CODE_EMPTY_PHONE_FIELD -> {
                            etPhone.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etPassword.error = show.errorMessage
                        }
                    }
                }
            })

            loginResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null) {
                    appPreferences.setAuthenticationToken(show.token)
                    appPreferences.setUser(show)
                    moveToGlobalNavigationActivity()
                }
            })
        }
    }

}