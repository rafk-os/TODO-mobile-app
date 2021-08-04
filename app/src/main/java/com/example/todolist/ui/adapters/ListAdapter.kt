package com.example.todolist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.model.Task


class ListAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<ListAdapter.BasicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.task_item,
            parent, false
        )

        return BasicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        val currentItem = taskList[position]

        holder.nameTextView.text = currentItem.taskName
        holder.dateTextView.text = currentItem.taskDate
        holder.categoryTextView.text = currentItem.taskCategory


    }

    override fun getItemCount() = taskList.size

    class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameText)
        val dateTextView: TextView = itemView.findViewById(R.id.dateText)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryText)
    }
}