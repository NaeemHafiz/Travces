package com.mtech.travces.data.remote.travces.model.data
import java.io.Serializable

class GetChildrenData : Serializable  {
    var id: String = ""
    var fname: String = ""
    var lname: String = ""
    var pickup_location: String = ""
    var drop_location: String = ""
    var pickup_time: String = ""
    var drop_time: String = ""
    var institute_name: String = ""
}