package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.model.Task

interface TaskRepository {

    fun addTask(task: Task)

    fun isThere(task: Task): Boolean

    fun getTask() : LiveData<List<Task>>

}