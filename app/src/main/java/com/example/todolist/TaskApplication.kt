package com.example.todolist

import android.app.Application
import com.example.todolist.data.db.Database
import com.example.todolist.data.db.DatabaseImplementation
import com.example.todolist.data.db.FakeTaskDao
import com.example.todolist.data.repository.RepositoryImplementation
import com.example.todolist.data.repository.TaskRepository
import com.example.todolist.ui.TaskViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TaskApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Database>() with singleton { DatabaseImplementation() }
        bind<FakeTaskDao>() with singleton { instance<Database>().taskDao }
        bind<TaskRepository>() with singleton { RepositoryImplementation(instance()) }
        bind() from provider { TaskViewModelFactory(instance()) }
    }
}