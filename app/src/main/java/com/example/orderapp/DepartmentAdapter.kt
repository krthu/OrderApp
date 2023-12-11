package com.example.orderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentAdapter(val context: Context, val departments: List<Department>): RecyclerView.Adapter<DepartmentAdapter.ItemViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    private val resources = context.resources

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView = layoutInflater.inflate(R.layout.department_row_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val department = departments[position]
        val name = department.name?.substring(0,1)?.uppercase() + department.name?.substring(1)
        holder.departmentNameView.text = name
    }

    override fun getItemCount() = departments.size

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val departmentNameView = view.findViewById<TextView>(R.id.deparmentNameTextView)
    }


}