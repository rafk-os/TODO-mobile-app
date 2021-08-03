package com.example.todolist.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.ErrorDialogFragment
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.utilities.InjectorUtils
import java.text.SimpleDateFormat
import java.util.*

class NewTaskActivity : AppCompatActivity() {
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtask)
        val cancelButton: Button = findViewById(R.id.CancelButton)
        val acceptButton: Button = findViewById(R.id.AcceptButton)
        val taskName: EditText = findViewById(R.id.TaskName)
        val taskDate: EditText = findViewById(R.id.TaskDate)
        val dropDownMenu: AutoCompleteTextView = findViewById(R.id.TaskCategoryMenu)
        val factory = InjectorUtils.provideTasksViewModelFactory()
        val viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        var dialog = ErrorDialogFragment()
        val dateSetListener =  object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDateinView()
            }
        }

        taskDate.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(this@NewTaskActivity,dateSetListener, cal.get(Calendar.YEAR
                    ),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

            }

        })


        cancelButton.setOnClickListener() {
            finish()
        }


        acceptButton.setOnClickListener() {

            if ( taskName.text.toString().isNotEmpty() && taskDate.text.toString().isNotEmpty() && dropDownMenu.text.toString() != "Select a Category"){
                val newTask = Task(
                    taskName.text.toString(),
                    taskDate.text.toString(),
                    dropDownMenu.text.toString()
                )
                viewModel.addTask(newTask)
                Toast.makeText(this, "New task has been added", Toast.LENGTH_LONG).show()
                finish()
            }
            else{
                dialog.show(supportFragmentManager,"errorDialog")
            }

        }

    }
    private fun updateDateinView(){
        val taskDate: EditText = findViewById(R.id.TaskDate)
        val format = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(format,Locale.getDefault())
        taskDate.setText(sdf.format(cal.time))

    }

}