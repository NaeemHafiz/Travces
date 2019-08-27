package com.mtech.travces.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mtech.travces.R
import com.mtech.travces.data.remote.travces.model.data.GetChildrenData


class ChildrenAdapter(var context: Context, var childrenList: List<GetChildrenData>, var callback: Callback) :
    RecyclerView.Adapter<ChildrenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_children_list, container, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) = holder.bind(i)

    override fun getItemCount(): Int = childrenList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvChildName = itemView.findViewById(R.id.tvchildname) as TextView
        var picktime = itemView.findViewById(R.id.picktime) as TextView
        var tvDropTime = itemView.findViewById(R.id.tvdroptime) as TextView
        var tvChildStatus = itemView.findViewById(R.id.childStatus) as TextView
        var tvpickuplocation = itemView.findViewById(R.id.pickup_location) as TextView
        var tvdroplocation = itemView.findViewById(R.id.drop_location) as TextView
        var institute_name = itemView.findViewById(R.id.institute_name) as TextView
        var cvItem = itemView.findViewById(R.id.llitemClick) as CardView

        fun bind(pos: Int) {
            tvChildName.text = childrenList[pos].fname + " " + childrenList[pos].lname
            picktime.text = "Pickup Time: " + childrenList[pos].pickup_time
            tvDropTime.text = "Drop Time: " + childrenList[pos].drop_time
            tvpickuplocation.text = "Pickup Location: " + childrenList[pos].pickup_location
            institute_name.text = "Institute Name:" + childrenList[pos].institute_name
            tvdroplocation.text = "Drop Location: " + childrenList[pos].drop_location
            tvChildStatus.text = context.getString(R.string.pendingvalue)
            initClickListeners()
        }
        private fun initClickListeners() {
            cvItem.setOnClickListener {
                callback.onItemClicked(adapterPosition)

            }
        }
    }
    interface Callback {
        fun onItemClicked(pos: Int)
        fun onDeleteClicked(pos: Int)
        fun oncvItemClicked(pos: Int)
    }
}