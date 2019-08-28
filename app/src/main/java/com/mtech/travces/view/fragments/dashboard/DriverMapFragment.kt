package com.mtech.travces.view.fragments.dashboard


import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.DriverData
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData
import com.mtech.travces.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_child.*


class DriverMapFragment : BaseFragment(), OnMapReadyCallback {


    override fun getLayoutId(): Int =
        R.layout.fragment_driver_map

    lateinit var mMap: GoogleMap
    lateinit var driverObj: DriverData
    var lat: String = ""
    var long: String = ""
    private fun getMyArguments() {

        val args = arguments
        if (args != null) {
            if (args.containsKey(Companion.KEY_DRIVER)) driverObj =
                (args.getSerializable(Companion.KEY_DRIVER)!! as DriverData)
            lat = driverObj.driver.vehicle_latitude
            long = driverObj.driver.vehicle_longitude
            Log.d("lat", lat + " " + long)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMyArguments()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var currentLatLng = LatLng(lat.toDouble(), long.toDouble())
        mMap.addMarker(MarkerOptions().position(currentLatLng).title("Pakistan"))
        val cameraPosition: CameraPosition = CameraPosition.Builder()
            .target(currentLatLng)
            .zoom(17f).build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    object Companion {
        @JvmStatic
        val TAG: String = DriverMapFragment::class.java.simpleName
        @JvmStatic
        val KEY_DRIVER = "driver"
    }

}
