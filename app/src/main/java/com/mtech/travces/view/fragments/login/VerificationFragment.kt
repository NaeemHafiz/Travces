package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import com.mtech.travces.R
import com.mtech.travces.view.fragments.base.BaseFragment

class VerificationFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_verification_phone


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bSubmit.setOnClickListener { userViewModel.forgotPassword(etEmail.text.toString()) }
//        tvResend.setOnClickListener { userViewModel.forgotPassword(etEmail.text.toString()) }

    }

}