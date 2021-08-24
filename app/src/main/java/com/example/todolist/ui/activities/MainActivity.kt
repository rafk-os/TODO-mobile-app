package com.example.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.TaskViewModelFactory
import com.example.todolist.ui.adapters.ListAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private lateinit var binding: ActivityMainBinding
    private var taskList = ArrayList<Task>()
    private val adapter = ListAdapter(taskList)
    private val factory: TaskViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.taskView.adapter = adapter
        binding.taskView.layoutManager = LinearLayoutManager(this)
        binding.taskView.setHasFixedSize(true)


        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        val newTaskIntent = Intent(this, NewTaskActivity::class.java)

        viewModel.getTasks(this).observe(this, { tasks ->
            if (tasks.isNotEmpty()) {
                taskList.clear()
                tasks.forEach {
                    taskList.add(it)
                }
            }
            adapter.notifyItemInserted(tasks.size - 1)
        })

        binding.addTaskButton.setOnClickListener {
            startActivity(newTaskIntent)
        }


    }

    override fun onResume() {
        super.onResume()
        if (taskList.isEmpty()) binding.noTaskText.visibility = View.VISIBLE
        else binding.noTaskText.visibility = View.GONE
    }

}