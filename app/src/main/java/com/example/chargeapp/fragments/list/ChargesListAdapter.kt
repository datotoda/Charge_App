package com.example.chargeapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chargeapp.R
import com.example.chargeapp.model.Charge
import kotlinx.android.synthetic.main.charge_item.view.*

class ChargesListAdapter: RecyclerView.Adapter<ChargesListAdapter.MyViewHolder>() {

        private var chargeList = emptyList<Charge>()

        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.itemTitleTextView
            val descriptionTextView: TextView = itemView.itemDescriptionTextView
            val costTextView: TextView = itemView.itemCostTextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.charge_item, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val currentItem = chargeList[position]
            holder.titleTextView.text = currentItem.getTitle()
            holder.descriptionTextView.text = currentItem.getDescription()
            holder.costTextView.text = currentItem.getCostString()

            holder.itemView.setOnClickListener {
                val action = ChargesListFragmentDirections.actionChargesListFragmentToUpdateChargeFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }

        }

        fun setData(product: List<Charge>){
            this.chargeList = product
            notifyDataSetChanged()

        }

        override fun getItemCount() = chargeList.size
}

// davit chinchaladze