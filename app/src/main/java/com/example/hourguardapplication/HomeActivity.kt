package com.example.hourguardapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.hourguardapplication.databinding.ActivityHomeBinding
import com.example.hourguardapplication.databinding.ActivitySignUpBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var Getstartedbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Getstartedbutton = findViewById(R.id.Getstartedbutton)

        Getstartedbutton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}