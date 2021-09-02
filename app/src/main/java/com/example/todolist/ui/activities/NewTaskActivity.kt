package com.example.todolist.ui.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.model.Task
import com.example.todolist.databinding.ActivityNewtaskBinding
import com.example.todolist.ui.DropdownViewModel
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.TaskViewModelFactory
import com.example.todolist.ui.fragments.ErrorDialogFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class NewTaskActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var binding: ActivityNewtaskBinding
    private val cal = Calendar.getInstance()
    private val factory: TaskViewModelFactory by instance()
    private val dialog = ErrorDialogFragment()

    // stworzenie listenera do kalendarza
    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.TaskDate.setText(sdf.format(cal.time))
        }

    private val taskDateFormat = "dd MMMM yyyy"
    private val sdf = SimpleDateFormat(taskDateFormat, Locale.getDefault())
    private val toastText = "New task has been added"
    private val dialogTag = "errorDialog"
    private var dropdownViewModel = DropdownViewModel()
    private var dropdownText = "Select a Category"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view binding
        binding = ActivityNewtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)


        // przygotowanie dropdown fragmentu
        dropdownViewModel = ViewModelProvider(this).get(DropdownViewModel::class.java)
        dropdownViewModel.getData().observe(this, { item -> dropdownText = item })


        // przygotowanie kalendarza
        binding.TaskDate.setOnClickListener {
            DatePickerDialog(
                this@NewTaskActivity, dateSetListener, cal.get(
                    Calendar.YEAR
                ), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        // cancel button listener
        binding.CancelButton.setOnClickListener {
            finish()
        }


        // accept button listener
        binding.AcceptButton.setOnClickListener {

            if (binding.TaskName.text.toString().isNotEmpty() && binding.TaskDate.text.toString()
                    .isNotEmpty() && dropdownText != "Select a Category"
            ) {

                val date: Date? = sdf.parse(binding.TaskDate.text.toString())


                viewModel.addTask(
                    this,
                    Task(
                        binding.TaskName.text.toString(),
                        date?.time ?: 1,
                        dropdownText
                    )
                )

                Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
                finish()
            } else dialog.show(supportFragmentManager, dialogTag)

        }
    }
}