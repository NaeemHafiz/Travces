package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.params.UpdateProfileParams
import com.mtech.travces.utils.extensions.*
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_update_profile.*

class UpdateProfileFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_update_profile
    lateinit var userViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        btnUpdateProfile.setOnClickListener {
            val params = UpdateProfileParams()
            params.fname = etFname.text.toString()
            params.lname = etLname.text.toString()
            params.email = etEmail.text.toString()
            params.password = etPassword.text.toString()
            params.password_confirmation = etCpassword.text.toString()
            params.cnic = etCnic.text.toString()
            params.address = etAddress.text.toString()
            params.phone = etPhone.text.toString()

            userViewModel.updateProfile(appPreferences.getUser().user.id.toString(), params)


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
                        ERROR_CODE_EMPTY_PHONE_FIELD -> {
                            etphone.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etpassword.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etcpassword.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_EMAIL_FIELD -> {
                            etemail.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CNIC -> {
                            etcnic.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_ADDRESS -> {
                            etaddress.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_FIRST_NAME_FIELD -> {
                            etfname.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_LAST_NAME_FIELD -> {
                            etlname.error = show.errorMessage
                        }
                    }
                }
            })
            updateProfileResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null) {
                    showToast(show)
                }
            })
        }
    }

}