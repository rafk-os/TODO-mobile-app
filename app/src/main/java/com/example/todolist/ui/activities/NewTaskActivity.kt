package com.example.todolist.ui.activities

//import android.view.View
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.data.model.Task
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
    private var cal = Calendar.getInstance()
    private val factory: TaskViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtask)
        val cancelButton: Button = findViewById(R.id.CancelButton)
        val acceptButton: Button = findViewById(R.id.AcceptButton)
        val taskName: EditText = findViewById(R.id.TaskName)
        val taskDate: EditText = findViewById(R.id.TaskDate)
        val dropDownMenu: AutoCompleteTextView = findViewById(R.id.TaskCategoryMenu)
        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        val dialog = ErrorDialogFragment()


        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateinView()
            }

        taskDate.setOnClickListener {
            DatePickerDialog(
                this@NewTaskActivity, dateSetListener, cal.get(
                    Calendar.YEAR
                ), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        cancelButton.setOnClickListener {
            finish()
        }


        acceptButton.setOnClickListener {

            if (taskName.text.toString().isNotEmpty() && taskDate.text.toString()
                    .isNotEmpty() && dropDownMenu.text.toString() != "Select a Category"
            ) {
                val newTask = Task(
                    taskName.text.toString(),
                    taskDate.text.toString(),
                    dropDownMenu.text.toString()
                )
                viewModel.addTask(newTask)
                Toast.makeText(this, "New task has been added", Toast.LENGTH_LONG).show()
                finish()
            } else dialog.show(supportFragmentManager, "errorDialog")


        }

    }

    private fun updateDateinView() {
        val taskDate: EditText = findViewById(R.id.TaskDate)
        val format = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        taskDate.setText(sdf.format(cal.time))

    }

}