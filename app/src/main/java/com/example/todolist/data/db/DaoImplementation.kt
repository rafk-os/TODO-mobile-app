package com.example.todolist.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.model.Task

class DaoImplementation : FakeTaskDao {

    private val taskList = mutableListOf<Task>()
    private val tasks =  MutableLiveData<List<Task>>()

    init {
        tasks.value = taskList
    }

    override fun addTask(task: Task) {
        taskList.add(task)
        tasks.value= taskList
    }

    override fun getTask(): LiveData<List<Task>> = tasks as LiveData<List<Task>>

    override fun isThere(task: Task): Boolean = taskList.contains(task)
}