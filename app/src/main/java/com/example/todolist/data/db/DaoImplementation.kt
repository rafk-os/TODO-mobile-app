package com.example.todolist.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.DataAccess
import com.example.todolist.data.model.Task

class DaoImplementation(private val dataAccess: DataAccess) : FakeTaskDao {

    private val taskList = mutableListOf<Task>()
    private val tasks = MutableLiveData<List<Task>>()

    init {
        tasks.value = taskList
    }

    override fun addTask(context: Context, task: Task) {
        taskList.add(task)
        tasks.value = taskList
        dataAccess.saveData(context, taskList)
    }

    override fun getTask(context: Context): LiveData<List<Task>> {

        val readList = dataAccess.readData(context)

        readList.forEach {
            if (!taskList.contains(it)) taskList.add(it)
        }
        return tasks
    }


}