package com.example.phpsql

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val txtWelcome: TextView = findViewById(R.id.txtWelcome)

        // Retrieve the email passed from MainActivity
        val email = intent.getStringExtra("email")
        txtWelcome.text = "Welcome, $email!"
    }
}
