package com.example.todolist.data.db

import com.example.todolist.data.DataAccess

class DatabaseImplementation(private val dataAccess: DataAccess) : Database {
    override val taskDao: FakeTaskDao = DaoImplementation(dataAccess)
}