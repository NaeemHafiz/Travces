package com.mtecsoft.swapme.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.GetDriverData


class DriverAdapter(var context: Context, var driversList: List<GetDriverData>, var callback: Callback) :
    RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_driver_list, container, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) = holder.bind(i)

    override fun getItemCount(): Int = driversList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvDriverName = itemView.findViewById(R.id.tvDriverName) as TextView
        var tvPickTime = itemView.findViewById(R.id.tvpicktim) as TextView
        var tvDropTime = itemView.findViewById(R.id.tvdroptime) as TextView
        var tvDriverStatus = itemView.findViewById(R.id.tvDriverStatus) as TextView
        var cvItem = itemView.findViewById(R.id.llItem) as CardView

        fun bind(pos: Int) {
            tvDriverName.text = driversList[pos].fname + driversList[pos].lname
            tvPickTime.text = driversList[pos].pick_time
            tvDropTime.text = driversList[pos].drop_time
            tvDriverStatus.text = context.getString(R.string.pendingvalue)
            initClickListeners()
        }
        private fun initClickListeners() {
            cvItem.setOnClickListener { callback.oncvItemClicked(adapterPosition) }
        }
    }
    interface Callback {
        fun onItemClicked(pos: Int)
        fun onDeleteClicked(pos: Int)
        fun oncvItemClicked(pos: Int)
    }
}