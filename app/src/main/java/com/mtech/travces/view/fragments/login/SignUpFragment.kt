package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.params.RegisterParams
import com.mtech.travces.utils.extensions.*
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_child.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_sign_up
    lateinit var userViewModel: UserViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        bSignUp.setOnClickListener {

            val params = RegisterParams()
            params.fname = etFname.text.toString()
            params.lname = etLname.text.toString()
            params.email = etEmail.text.toString()
            params.password = etPassword.text.toString()
            params.password_confirmation = etCpassword.text.toString()
            params.cnic = etCnic.text.toString()
            params.address = etAddress.text.toString()
            params.phone = etPhone.text.toString()

            userViewModel.register(params)
        }
//        Navigation.createNavigateOnClickListener(R.id.action_signUpFragment_to_verificationFragment)
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
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etCpassword.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_EMAIL_FIELD -> {
                            etEmail.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CNIC -> {
                            etCnic.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_ADDRESS -> {
                            etAddress.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_FIRST_NAME_FIELD -> {
                            etFname.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_LAST_NAME_FIELD -> {
                            etLname.error = show.errorMessage
                        }

                    }
                }
            })

            registerResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null) {

                    showToast(show.toString())
                    //  moveToGlobalNavigationActivity()
                }
            })
        }
    }
}