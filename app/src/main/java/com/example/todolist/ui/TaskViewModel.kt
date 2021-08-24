package com.example.todolist.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.todolist.data.db.FakeTaskDao
import com.example.todolist.data.model.Task

class TaskViewModel(private val taskDao: FakeTaskDao) : ViewModel() {


    fun getTasks(context: Context) = taskDao.getTask(context)

    fun addTask(context: Context, task: Task) = taskDao.addTask(context, task)

}