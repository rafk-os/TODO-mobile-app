package com.example.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.model.Task
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.adapters.ListAdapter
import com.example.todolist.utilities.InjectorUtils


class MainActivity : AppCompatActivity() {
    private var testList = ArrayList<Task>()
    private val adapter = ListAdapter(testList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addTaskButton: Button = findViewById(R.id.addTaskButton)
        val listView: RecyclerView = findViewById(R.id.taskView)

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
        listView.setHasFixedSize(true)


        val factory = InjectorUtils.provideTasksViewModelFactory()
        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        viewModel.getTasks().observe(this, { tasks ->
            if (tasks.isNotEmpty()) {
                testList.clear()
                tasks.forEach {
                    testList.add(it)
                }
            }
            adapter.notifyItemInserted(tasks.size - 1)
        })

        addTaskButton.setOnClickListener {

            val intent = Intent(this, NewTaskActivity::class.java)

            startActivity(intent)

        }


    }

    override fun onResume() {
        super.onResume()
        val noTaskView: TextView = findViewById(R.id.noTaskText)
        if (testList.isEmpty()) noTaskView.visibility = View.VISIBLE
        else noTaskView.visibility = View.GONE


    }

}