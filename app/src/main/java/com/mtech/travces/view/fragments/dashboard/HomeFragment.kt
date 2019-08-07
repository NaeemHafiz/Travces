package com.mtech.travces.view.fragments.dashboard

import android.os.Bundle
import android.view.View
import com.mtech.travces.R
import com.mtech.travces.view.fragments.base.BaseFragment

class HomeFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_home


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bSubmit.setOnClickListener { userViewModel.forgotPassword(etEmail.text.toString()) }

    }
}