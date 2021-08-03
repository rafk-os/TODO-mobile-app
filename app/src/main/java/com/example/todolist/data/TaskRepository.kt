package com.example.todolist.data

class TaskRepository private constructor(private val taskDao: FakeTaskDao) {
    fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    fun getTasks() = taskDao.getTask()

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: FakeTaskDao) = instance ?: synchronized(this) {
            instance ?: TaskRepository(taskDao).also { instance = it }
        }
    }


}