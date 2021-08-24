package com.example.todolist.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.todolist.data.model.Task

interface FakeTaskDao {


    fun addTask(context: Context, task: Task)

    fun getTask(context: Context): LiveData<List<Task>>
}