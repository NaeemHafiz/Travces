package com.mtech.travces.view.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.DriverData
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.view.activities.GlobalNavigationActivity
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import com.mtech.travces.view.adapters.ChildrenAdapter
import com.mtecsoft.swapme.view.adapters.DriverAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), DriverAdapter.Callback {


    override fun getLayoutId(): Int = R.layout.fragment_home

    var lastScrollPosition = -1
    lateinit var driverAdapter: DriverAdapter
    var driverList = ArrayList<DriverData>()
    lateinit var userViewModel: UserViewModel
    // Initializing an empty ArrayList to be filled with animals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModel()
        userViewModel.getDriverList(appPreferences.getUser().user.id.toString())
        initNotesAdapter()
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
            getDriverListResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show?.data != null) {
                    driverList.clear()
                    for (temp in show.data.iterator()) {

                        driverList.add(temp)
                        Log.e("Driver data",driverList[0].children[0].institute_name)
                    }
                }
                driverAdapter.notifyDataSetChanged()
            })
        }
    }

    private fun initNotesAdapter() {
        driverAdapter = DriverAdapter(context as BaseActivity, driverList, this)
        rvDrivers.layoutManager = LinearLayoutManager(context)
        rvDrivers.adapter = driverAdapter
    }

    override fun onItemClicked(pos: Int) {
//        val args = Bundle()
//        args.putSerializable("data", driverList[pos])
        (activity as GlobalNavigationActivity).navController.navigate(
            R.id.action_homeFragment_to_driverMapFragment
        )
    }


    override fun onDeleteClicked(pos: Int) {
    }

    override fun oncvItemClicked(pos: Int) {
    }
}