package com.example.todolist.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.model.Task

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

    fun isThere(task: Task): Boolean {
        return taskList.contains(task)
    }

    fun getTask() = tasks as LiveData<List<Task>>
}