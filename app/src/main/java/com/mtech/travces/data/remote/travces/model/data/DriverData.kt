package com.mtech.travces.data.remote.travces.model.data

import java.io.Serializable

class DriverData : Serializable {
    val driver = Driver()
    val children: List<Child> = ArrayList()

    inner class Driver {
        var id: Int = -1
        var fname: String = ""
        var lname: String = ""
        var phone: String = ""
        var cnic: String = ""
        var address: String = ""
        var vehicle_number: String = ""
        var vehicle_model: String = ""
        var vehicle_latitude: String = ""
        var vehicle_longitude: String = ""
    }

    inner class Child {
        var id: Int = -1
        var fname: String = ""
        var lname: String = ""
        var pickup_location: String = ""
        var drop_location: String = ""
        var pickup_time: String = ""
        var drop_time: String = ""
        var institute_name: String = ""
        var parent_id: Int = -1

    }

}