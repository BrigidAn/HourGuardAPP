package com.example.hourguardapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTaskActivity : AppCompatActivity() {
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var dateTimeText: TextView
    private lateinit var prioritySpinner: Spinner
    private lateinit var saveButton: Button
    private lateinit var dateTimeButton: Button

    private var selectedDateTime: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        titleInput = findViewById(R.id.etTaskTitle)
        descriptionInput = findViewById(R.id.etTaskDescription)
        dateTimeText = findViewById(R.id.tvSelectedDateTime)
        prioritySpinner = findViewById(R.id.spinnerPriority)
        saveButton = findViewById(R.id.btnSaveTask)
        dateTimeButton = findViewById(R.id.btnPickDateTime)

        val priorities = arrayOf("Low", "Medium", "High")
        prioritySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, priorities)

        dateTimeButton.setOnClickListener {
            pickDateTime()
        }

        saveButton.setOnClickListener {
            val taskTitle = titleInput.text.toString()
            val taskDescription = descriptionInput.text.toString()
            val taskPriority = prioritySpinner.selectedItem.toString()

            if (taskTitle.isBlank() || selectedDateTime == null) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save logic (you can later add to database or pass back)
            Toast.makeText(this, "Task Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun pickDateTime() {
        val now = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            TimePickerDialog(this, { _, hourOfDay, minute ->
                selectedDateTime = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth, hourOfDay, minute)
                }
                dateTimeText.text = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault()).format(selectedDateTime!!.time)
            }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false).show()
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show()
    }
}