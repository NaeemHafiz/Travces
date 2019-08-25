package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.utils.extensions.ERROR_CODE_EMPTY_CONFIRM_PASSWORD
import com.mtech.travces.utils.extensions.ERROR_CODE_EMPTY_EMAIL_FIELD
import com.mtech.travces.utils.extensions.ERROR_CODE_EMPTY_PHONE_FIELD
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_login.*


class ForgotPasswordFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_forgot_password
    lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        btnSubmit.setOnClickListener {
            userViewModel.forgotPassword(etEmail.text.toString())
            Navigation.createNavigateOnClickListener(R.id.action_forgotPasswordFragment_to_verificationFragment)
        }
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
                        ERROR_CODE_EMPTY_EMAIL_FIELD -> {
                            etEmail.error = show.errorMessage
                        }
                    }
                }
            })

            forgotPasswordResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null) {
                    showToast(show)
                    //moveToGlobalNavigationActivity()
                }
            })
        }
    }
}