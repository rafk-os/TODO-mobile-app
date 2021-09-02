package com.example.todolist.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.todolist.data.DataAccessImplementation
import com.example.todolist.data.db.DatabaseImplementation
import com.example.todolist.data.model.Task
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


class TaskViewModelTest {

    private lateinit var taskDatabase: DatabaseImplementation
    private lateinit var taskViewModel: TaskViewModel
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val dataAccess = DataAccessImplementation()
    private var charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')


    @Before
    fun setup() {
        taskDatabase = DatabaseImplementation(dataAccess)
        taskViewModel = TaskViewModel(taskDatabase.taskDao)

    }

    @Test
    fun isViewModelWorking() {

        taskViewModel.addTask(context, Task("Cos", 10012, "Job"))
        val result = taskViewModel.getTasks(context).value
        assertThat(result != null).isTrue()

    }

    @Test
    fun largeDataInput() {
        for (i in 1..100) {
            taskViewModel.addTask(context, Task((1..10)
                .map { Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString(""),
                Random.nextLong(100000, 100000000),
                when (Random.nextInt(1, 3)) {
                    1 -> "Shopping"
                    2 -> "Job"
                    else -> "Other"
                }
            ))

        }

        val result = taskViewModel.getTasks(context).value
        assertThat(result?.size == 100).isTrue()

    }

    @Test
    fun dataStorage() {

        val testSubject = Task((1..10)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString(""), Random.nextLong(100000, 100000000), when (Random.nextInt(1, 3)) {
            1 -> "Shopping"
            2 -> "Job"
            else -> "Other"
        })

        dataAccess.saveData(context, mutableListOf(testSubject))
        val result = dataAccess.readData(context)

        assertThat(result.contains(testSubject)).isTrue()


    }


}