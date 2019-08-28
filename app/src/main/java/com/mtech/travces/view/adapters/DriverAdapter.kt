package com.mtecsoft.swapme.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.DriverData


class DriverAdapter(
    var context: Context,
    var driversList: List<DriverData>,
    var callback: Callback
) :
    RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_item_drivers_list,
                container,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) = holder.bind(i)

    override fun getItemCount(): Int = driversList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvDriverName = itemView.findViewById(R.id.tvDriverName) as TextView
        var tvPickTime = itemView.findViewById(R.id.tvpicktime) as TextView
        var tvDropTime = itemView.findViewById(R.id.tvDroptime) as TextView
        var tvDriverStatus = itemView.findViewById(R.id.tvstatus) as TextView
        var pickup_location = itemView.findViewById(R.id.pickup_location) as TextView
        var drop_location = itemView.findViewById(R.id.drop_location) as TextView
        var institute_name = itemView.findViewById(R.id.institute_name) as TextView
        var cvItem = itemView.findViewById(R.id.itemclik) as CardView

        //
        @SuppressLint("SetTextI18n")
        fun bind(pos: Int) {
            val driver: DriverData = driversList[pos]
            tvDriverName.text = "${driver.driver.fname} ${driver.driver.lname}"
            tvPickTime.text = driver.children[0].pickup_time
            tvDropTime.text = driver.children[0].drop_time
            pickup_location.text = driver.children[0].pickup_location
            drop_location.text = driver.children[0].drop_location
            institute_name.text = driver.children[0].institute_name
            tvDriverStatus.text = context.getString(R.string.pendingvalue)
            initClickListeners()
        }

        //
        private fun initClickListeners() {
            cvItem.setOnClickListener { callback.onItemClicked(adapterPosition) }
        }
    }

    interface Callback {
        fun onItemClicked(pos: Int)
        fun onDeleteClicked(pos: Int)
        fun oncvItemClicked(pos: Int)
    }
}