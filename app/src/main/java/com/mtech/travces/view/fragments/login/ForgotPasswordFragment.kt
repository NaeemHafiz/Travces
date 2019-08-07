package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ForgotPasswordFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_forgot_password


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bSubmit.setOnClickListener { Navigation.createNavigateOnClickListener(R.id.action_forgotPasswordFragment_to_verificationFragment) }

    }

}