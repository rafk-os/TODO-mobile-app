package com.example.todolist.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.DataAccess
import com.example.todolist.data.model.Task

class DaoImplementation(private val dataAccess: DataAccess) : FakeTaskDao {

    private val taskList = mutableListOf<Task>()
    private val tasks = MutableLiveData<List<Task>>()

    init {
        tasks.value = taskList

        /* komenda działająca przy testowaniu
       tasks.postValue(taskList) */
    }

    override fun addTask(context: Context, task: Task) {
        taskList.add(task)
        tasks.value = taskList

        /* komenda działająca przy testowaniu
       tasks.postValue(taskList) */


        // zapisanie danych do pliku aplikacji
        dataAccess.saveData(context, taskList)
    }

    override fun getTask(context: Context): LiveData<List<Task>> {

        // odczytanie danych do pliku aplikacji
        val readList = dataAccess.readData(context)

        // sprawdzenie czy zadania nie są już w tasklist
        readList.forEach {
            if (!taskList.contains(it)) taskList.add(it)
        }
        return tasks
    }


}