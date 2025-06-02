package com.example.hourguardapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {

    private lateinit var textViewTimer: TextView
    private lateinit var buttonStartPause: Button
    private lateinit var buttonReset: Button

    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var timeLeftInMillis = START_TIME_IN_MILLIS

    companion object {
        private const val START_TIME_IN_MILLIS: Long = 25 * 60 * 1000 // 25 minutes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        textViewTimer = findViewById(R.id.textViewTimer)
        buttonStartPause = findViewById(R.id.buttonStartPause)
        buttonReset = findViewById(R.id.buttonReset)

        updateTimerText()

        buttonStartPause.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        buttonReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isRunning = false
                buttonStartPause.text = "Start"
                // Optional: Play a sound or show notification
            }
        }.start()

        isRunning = true
        buttonStartPause.text = "Pause"
    }

    private fun pauseTimer() {
        timer?.cancel()
        isRunning = false
        buttonStartPause.text = "Start"
    }

    private fun resetTimer() {
        timer?.cancel()
        timeLeftInMillis = START_TIME_IN_MILLIS
        updateTimerText()
        isRunning = false
        buttonStartPause.text = "Start"
    }

    private fun updateTimerText() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) % 60
        textViewTimer.text = String.format("%02d:%02d", minutes, seconds)
    }
}