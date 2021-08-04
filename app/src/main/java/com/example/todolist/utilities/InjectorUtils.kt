package com.example.todolist.utilities

import com.example.todolist.data.db.FakeDatabase
import com.example.todolist.data.repository.TaskRepository
import com.example.todolist.ui.TaskViewModelFactory

object InjectorUtils {

    fun provideTasksViewModelFactory(): TaskViewModelFactory {
        val taskRepository = TaskRepository.getInstance(FakeDatabase.getInstance().taskDao)
        return TaskViewModelFactory(taskRepository)
    }
}