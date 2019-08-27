package com.mtech.travces.view.fragments.dashboard

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.data.remote.travces.model.params.ChildParams
import com.mtech.travces.data.remote.travces.model.params.UpdateChildParams
import com.mtech.travces.utils.extensions.*
import com.mtech.travces.view.dialogs.CustomTimePickerDialog
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.view.fragments.dashboard.AddChildFragment.Companion.KEY_CHILD
import com.mtech.travces.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_child.*
import java.util.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class AddChildFragment : BaseFragment(), TimePickerDialog.OnTimeSetListener {

    override fun getLayoutId(): Int = R.layout.fragment_add_child
    lateinit var childObj: GetChildrenData
    lateinit var userViewModel: UserViewModel
    var forStartTime = false

    private fun getMyArguments() {

        val args = arguments
        if (args != null) {
            if (args.containsKey(KEY_CHILD)) childObj =
                (args.getSerializable(KEY_CHILD)!! as GetChildrenData)

            etFcname.setText(childObj.fname)
            etLcname.setText(childObj.lname)
            etPclocation.setText(childObj.pickup_location)
            etDlocation.setText(childObj.drop_location)
            etPtime.setText(childObj.pickup_time)
            etDtime.setText(childObj.drop_time)
            etIname.setText(childObj.institute_name)
//            etDescription.setText(noteItem.description)
            bAdd.text = "Update"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMyArguments()
        attachViewModel()
        bAdd.setOnClickListener {
            if (bAdd.text.toString().equals("Add")) {
                val params = ChildParams()
                params.fname = etFcname.text.toString()
                params.lname = etLcname.text.toString()
                params.pickup_location = etPclocation.text.toString()
                params.drop_location = etDlocation.text.toString()
                params.pickup_time = etPtime.text.toString()
                params.drop_time = etDtime.text.toString()
                params.institute_name = etIname.text.toString()
                params.parent_id = appPreferences.getUser().user.id.toString()
                userViewModel.addChild(params)
            } else {
                val params = UpdateChildParams()
                params.fname = etFcname.text.toString()
                params.lname = etLcname.text.toString()
                params.pickup_location = etPclocation.text.toString()
                params.drop_location = etDlocation.text.toString()
                params.pickup_time = etPtime.text.toString()
                params.drop_time = etDtime.text.toString()
                params.institute_name = etIname.text.toString()
                params.child_id = childObj.id
                userViewModel.updateChildProfile(params)
            }
        }

        etPtime.setOnClickListener {
            forStartTime = true
            showPickDropTimePicker()
        }
        etDtime.setOnClickListener {
            forStartTime = false
            showPickDropTimePicker()
        }

//        llPickTime.setOnClickListener {
//            forStartTime = true
//            showPickupTimePicker()
//        }
//        llDropTime.setOnClickListener {
//            forStartTime = false
//            showDropTimePicker()
//        }
    }

    private fun showPickDropTimePicker() {
        val calendar = Calendar.getInstance()

        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        CustomTimePickerDialog(context, this, hourOfDay, minute, false).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        if (forStartTime) {
            etPtime.text =
                "${if (hourOfDay < 10) "0$hourOfDay" else hourOfDay}" + ":${if (minute < 10) "0$minute" else minute}"
        } else {
            etDtime.text =
                "${if (hourOfDay < 10) "0$hourOfDay" else hourOfDay}" + ":${if (minute < 10) "0$minute" else minute}"
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
                            etFcname.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etLcname.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CONFIRM_PASSWORD -> {
                            etPclocation.error = show.errorMessage
                        }
                        ERROR_CODE_PICKUP_LOCATION_NAME -> {
                            etPclocation.error = show.errorMessage
                        }
                        ERROR_CODE_DROP_LOCATION_NAME -> {
                            etDlocation.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_EMAIL_FIELD -> {
                            etDlocation.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_CNIC -> {
                            etPtime.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_ADDRESS -> {
                            etDtime.error = show.errorMessage
                        }
                        ERROR_CODE_EMPTY_FIRST_NAME_FIELD -> {
                            etIname.error = show.errorMessage
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

    object Companion {
        @JvmStatic
        val TAG: String = AddChildFragment::class.java.simpleName
        @JvmStatic
        val KEY_CHILD = "child"
    }

}
