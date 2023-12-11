package com.example.orderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentAdapter(val context: Context, val departments: List<Department>): RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    private val resources = context.resources

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.department_row_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DepartmentAdapter.ViewHolder, position: Int) {
        val department = departments[position]
        holder.departmentNameView.text = department.name
    }

    override fun getItemCount() = departments.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val departmentNameView = itemView.findViewById<TextView>(R.id.deparmentNameTextView)
    }


}