package com.example.tokopancingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopancingapp.R
import com.example.tokopancingapp.model.pancing

class PancingListAdapter(
    private val onItemClickListener: (pancing) -> Unit
): ListAdapter<pancing, PancingListAdapter.pancingViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pancingViewHolder {
       return pancingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: pancingViewHolder, position: Int) {
        val pancing = getItem(position)
        holder.bind(pancing)
        holder.itemView.setOnClickListener {
            onItemClickListener(pancing)
        }
    }

    class pancingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val nameTextView: TextView = itemView.findViewById(R.id.namaeditTextText)
        private val addressTextView: TextView = itemView.findViewById(R.id.addresseditTextText)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceText)

        fun bind(pancing: pancing?) {
            nameTextView.text = pancing?.name
            addressTextView.text = pancing?.address
            priceTextView.text = pancing?.price

        }

        companion object {
            fun create(parent: ViewGroup): PancingListAdapter.pancingViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pancing, parent, false)
                return pancingViewHolder(view)
            }
        }
    }
    companion object{
        private val WORDS_COMPARATOR = object :DiffUtil.ItemCallback<pancing>(){
            override fun areItemsTheSame(oldItem: pancing, newItem: pancing): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: pancing, newItem: pancing): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}