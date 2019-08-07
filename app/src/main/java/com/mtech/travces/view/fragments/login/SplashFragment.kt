package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SplashFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() {
        bLogin.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_splashFragment_to_loginFragment))
        bSignUp.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_splashFragment_to_signUpFragment))
    }
}