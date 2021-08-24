package com.example.todolist

import android.app.Application
import com.example.todolist.data.DataAccess
import com.example.todolist.data.DataAccessImplementation
import com.example.todolist.data.db.Database
import com.example.todolist.data.db.DatabaseImplementation
import com.example.todolist.data.db.FakeTaskDao
import com.example.todolist.ui.TaskViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TaskApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<DataAccess>() with singleton { DataAccessImplementation() }
        bind<Database>() with singleton { DatabaseImplementation(instance()) }
        bind<FakeTaskDao>() with singleton { instance<Database>().taskDao }
        bind() from provider { TaskViewModelFactory(instance()) }
    }
}