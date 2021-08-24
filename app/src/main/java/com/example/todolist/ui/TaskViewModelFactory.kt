package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.db.FakeTaskDao

class TaskViewModelFactory(private val taskDao: FakeTaskDao) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(taskDao) as T
    }
}