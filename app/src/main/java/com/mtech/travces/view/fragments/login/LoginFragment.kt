package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bLogin.setOnClickListener { moveToGlobalNavigationActivity() }

        tvForgotPassword.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_forgotPasswordFragment))
    }

}