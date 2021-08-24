package com.example.todolist.data

import android.content.Context
import com.example.todolist.data.model.Task


interface DataAccess {

    fun saveData(context: Context, list: MutableList<Task>)

    fun readData(context: Context): MutableList<Task>
}