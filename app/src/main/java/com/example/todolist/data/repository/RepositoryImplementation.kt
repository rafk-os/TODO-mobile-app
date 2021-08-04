package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.db.FakeTaskDao
import com.example.todolist.data.model.Task

class RepositoryImplementation(private val taskDao: FakeTaskDao) : TaskRepository {
    override fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    override fun getTask(): LiveData<List<Task>> = taskDao.getTask()

    override fun isThere(task: Task): Boolean =  taskDao.isThere(task)
}