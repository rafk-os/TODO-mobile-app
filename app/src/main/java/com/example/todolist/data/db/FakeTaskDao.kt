package com.example.todolist.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.model.Task

interface FakeTaskDao {


    fun addTask(task: Task)

    fun isThere(task: Task): Boolean

    fun getTask() : LiveData<List<Task>>
}