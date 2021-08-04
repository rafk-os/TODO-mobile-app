package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {


    fun getTasks() = taskRepository.getTask()

    fun addTask(task: Task) = taskRepository.addTask(task)

    fun isThere(task: Task): Boolean = taskRepository.isThere(task)
}