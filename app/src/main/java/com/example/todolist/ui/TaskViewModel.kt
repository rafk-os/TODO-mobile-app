package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {


    fun getTasks() = taskRepository.getTasks()

    fun addTask(task: Task) = taskRepository.addTask(task)
}