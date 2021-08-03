package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeTaskDao {
    private val taskList = mutableListOf<Task>()
    private val tasks = MutableLiveData<List<Task>>()

    init {
        tasks.value = taskList
    }

    fun addTask(task: Task) {
        taskList.add(task)
        tasks.value = taskList

    }

    fun getTask() = tasks as LiveData<List<Task>>
}