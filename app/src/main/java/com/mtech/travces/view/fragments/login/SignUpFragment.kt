package com.mtech.travces.view.fragments.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.mtech.travces.R
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_sign_up


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bSignUp.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_signUpFragment_to_verificationFragment))

    }
}