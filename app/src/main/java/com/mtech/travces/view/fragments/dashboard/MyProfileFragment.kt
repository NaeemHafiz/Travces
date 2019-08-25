package com.mtech.travces.view.fragments.dashboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mtech.travces.R
import com.mtech.travces.view.activities.GlobalNavigationActivity
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_profile.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyProfileFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_my_profile
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAddChild.setOnClickListener{
            (activity as GlobalNavigationActivity).navController.navigate(R.id.action_myProfileFragment_to_addChildFragment)
        }


    }

}
