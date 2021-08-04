package com.example.todolist.ui.activities

//import androidx.lifecycle.Observer
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.PrefConfig
import com.example.todolist.data.model.Task
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.TaskViewModelFactory
import com.example.todolist.ui.adapters.ListAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()


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
        val prefConfig = PrefConfig()
        val factory: TaskViewModelFactory by instance()
        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        val  prefData = prefConfig.readListFromPref(applicationContext)
        if(prefData.isNotEmpty()) prefData.forEach { testList.add(it) }

        if (testList.isNotEmpty()) {
            testList.forEach {
                if(!(viewModel.isThere(it))) viewModel.addTask(it)
            }
        }
        viewModel.getTasks().observe(this, { tasks ->
            if (tasks.isNotEmpty()) {
                testList.clear()
                tasks.forEach {
                    testList.add(it)
                }
            }

            prefConfig.writeListInPref(applicationContext, testList)
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