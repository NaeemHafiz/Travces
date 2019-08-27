package com.mtech.travces.view.fragments.dashboard


import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mtech.travces.R
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.view.fragments.base.BaseFragment


class DriverMapFragment : BaseFragment(), OnMapReadyCallback {


    override fun getLayoutId(): Int =
        com.mtech.travces.R.layout.fragment_driver_map

    lateinit var mMap: GoogleMap
    var mapFragment : SupportMapFragment?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = fragmentManager!!.findFragmentById(R.id.googleMap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var Pakistan = LatLng(31.0, 71.0)
        mMap.addMarker(MarkerOptions().position(Pakistan).title("Pakistan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pakistan))
    }


}
