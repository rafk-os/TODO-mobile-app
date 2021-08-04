package com.example.todolist.data.db

class DatabaseImplementation : Database {
    override val taskDao: FakeTaskDao =  DaoImplementation()
}