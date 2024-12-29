package com.example.phpsql

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class UserDetailActivity : AppCompatActivity() {

    private lateinit var tvEmail: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        tvEmail = findViewById(R.id.tvEmail)
        tvUsername = findViewById(R.id.tvUsername)
        tvPassword = findViewById(R.id.tvPassword)

        val email = intent.getStringExtra("email")

        // Fetch the user data (email, username, etc.) based on the email
        // You can make an API call here to get more user details based on the email if needed

        tvEmail.text = "Email: $email"
    }
}
