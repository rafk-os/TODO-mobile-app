package com.example.todolist.utilities

import com.example.todolist.data.FakeDatabase
import com.example.todolist.data.TaskRepository
import com.example.todolist.ui.TaskViewModelFactory

object IncjectorUtils {

    fun provideTasksViewModelFactory(): TaskViewModelFactory {
        val taskRepository= TaskRepository.getInstance(FakeDatabase.getInstance().taskDao)
        return TaskViewModelFactory(taskRepository)
    }
}