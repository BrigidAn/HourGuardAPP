package com.example.hourguardapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class DashboardActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView
    private lateinit var todayTasksTextView: TextView
    private lateinit var nextTaskTextView: TextView
    private lateinit var hoursWorkedTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var motivationTextView: TextView
    private lateinit var addTaskButton: Button
    private lateinit var startTimerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        greetingTextView = findViewById(R.id.greetingTextView)
        todayTasksTextView = findViewById(R.id.todayTasksTextView)
        nextTaskTextView = findViewById(R.id.nextTaskTextView)
        hoursWorkedTextView = findViewById(R.id.hoursWorkedTextView)
        progressBar = findViewById(R.id.progressBar)
        motivationTextView = findViewById(R.id.motivationTextView)
        addTaskButton = findViewById(R.id.addTaskButton)
        startTimerButton = findViewById(R.id.startTimerButton)

        val username = "Alex" // get from auth or preferences
        greetingTextView.text = getGreetingMessage() + ", $username!"

        todayTasksTextView.text = "You have 3 tasks today"
        nextTaskTextView.text = "Next: Submit design mockup at 2:00 PM"
        progressBar.progress = 50
        hoursWorkedTextView.text = "Worked: 3.5 hrs today"
        motivationTextView.text = "“Small steps every day.”"

        addTaskButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        startTimerButton.setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
    }

    private fun getGreetingMessage(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            hour < 12 -> "Good Morning"
            hour < 18 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}
