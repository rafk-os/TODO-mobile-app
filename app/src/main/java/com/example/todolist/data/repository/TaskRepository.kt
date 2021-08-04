package com.example.todolist.data.repository

import com.example.todolist.data.db.FakeTaskDao
import com.example.todolist.data.model.Task

class TaskRepository private constructor(private val taskDao: FakeTaskDao) {
    fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    fun getTasks() = taskDao.getTask()

    fun isThere(task: Task) : Boolean = taskDao.isThere(task)

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: FakeTaskDao) = instance ?: synchronized(this) {
            instance ?: TaskRepository(taskDao).also { instance = it }
        }
    }


}