package com.example.orderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BrandAdapter(context: Context, val brands: List<Brand>, val fragment: BrandFragment): RecyclerView.Adapter<BrandAdapter.ItemViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    inner class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    fragment.onBrandClick(brands[position].brandId)
                }
            }
        }


        val brandNameTextView = itemView.findViewById<TextView>(R.id.brandNameTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView = inflater.inflate(R.layout.brand_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount() = brands.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val brand = brands[position]
        val name = brand.name?.substring(0,1)?.uppercase() + brand.name?.substring(1)
        holder.brandNameTextView.text = name
    }


}