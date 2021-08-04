package com.example.todolist.data.db

class FakeDatabase private constructor() {

    var taskDao = FakeTaskDao()
        private set

    companion object {
        @Volatile
        private var instance: FakeDatabase? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FakeDatabase().also { instance = it }
        }
    }
}