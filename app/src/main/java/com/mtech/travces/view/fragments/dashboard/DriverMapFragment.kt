package com.mtech.travces.view.fragments.dashboard


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.common.net.MediaType
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.DriverData
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.data.remote.travces.model.params.PusherParams
import com.mtech.travces.utils.extensions.*
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.view.fragments.base.BaseFragment
import com.mtech.travces.viewModel.UserViewModel
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.fragment_add_child.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import okhttp3.RequestBody
import org.json.JSONObject


class DriverMapFragment : BaseFragment(), OnMapReadyCallback {


    override fun getLayoutId(): Int =
        R.layout.fragment_driver_map

    lateinit var userViewModel: UserViewModel

    lateinit var mMap: GoogleMap
    lateinit var driverObj: DriverData
    var lat: String = ""
    var long: String = ""
    private lateinit var cameraPosition: CameraPosition
    private lateinit var markerOptions: MarkerOptions
    private lateinit var marker: Marker
    lateinit var pusher: Pusher
    var defaultLongitude = -122.088426
    var defaultLatitude = 37.388064
    private fun getMyArguments() {

        val args = arguments
        if (args != null) {
            if (args.containsKey(Companion.KEY_DRIVER)) driverObj =
                (args.getSerializable(Companion.KEY_DRIVER)!! as DriverData)
            lat = driverObj.driver.vehicle_latitude
            long = driverObj.driver.vehicle_longitude

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        markerOptions = MarkerOptions()
        getMyArguments()
        attachViewModel()
        val params = PusherParams()
        params.latitude = lat
        params.longitude = long
        userViewModel.sendCoordinates(params)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var currentLatLng = LatLng(lat.toDouble(), long.toDouble())
        mMap.addMarker(markerOptions.position(currentLatLng).title("Pakistan"))
//        cameraPosition = CameraPosition.Builder()
//            .target(currentLatLng)
//            .zoom(17f).build()
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    object Companion {
        @JvmStatic
        val TAG: String = DriverMapFragment::class.java.simpleName
        @JvmStatic
        val KEY_DRIVER = "driver"
    }
//    private fun callServerToSimulate() {
//        val jsonObject = JSONObject()
//        jsonObject.put("latitude",defaultLatitude)
//        jsonObject.put("longitude",defaultLongitude)
//
//        val body = RequestBody.create(
//            MediaType.parse("application/json"),
//            jsonObject.toString()
//        )
//
//        getRetrofitObject().sendCoordinates(body).enqueue(object:Callback<String>{
//            override fun onResponse(call: Call<String>?, response: Response<String>?) {
//                Log.d("TAG",response!!.body().toString())
//            }
//
//            override fun onFailure(call: Call<String>?, t: Throwable?) {
//                Log.d("TAG",t!!.message)
//            }
//        })
//    }

    private fun setupPusher() {
        val options = PusherOptions()
        options.setCluster("ap2")
        pusher = Pusher("8332aaa9ad861498c559", options)

        val channel = pusher.subscribe("my-channel")

        channel.bind("new-values") { channelName, eventName, data ->
            val jsonObject = JSONObject(data)
            val lat: Double = jsonObject.getString("latitude").toDouble()
            val lon: Double = jsonObject.getString("longitude").toDouble()


            (activity as BaseActivity).runOnUiThread {
                val newLatLng = LatLng(lat, lon.toDouble())
                marker.position = newLatLng
                cameraPosition = CameraPosition.Builder()
                    .target(newLatLng)
                    .zoom(17f).build()
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
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


            sendCoordinatesResponse.observe(viewLifecycleOwner, Observer {
                val show = it?.getContentIfNotHandled()
                if (show != null) {
                    showToast(show.toString())
                    //  moveToGlobalNavigationActivity()
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        pusher.connect()
    }

    override fun onPause() {
        super.onPause()
        pusher.disconnect()
    }
}
