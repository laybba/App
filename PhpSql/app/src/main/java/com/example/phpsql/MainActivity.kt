package com.example.phpsql

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var edtUsername: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnRegister: Button
    lateinit var btnLogin: Button
    lateinit var btnForgotPassword: Button
    lateinit var btnDisplayUsers: Button
    lateinit var tvRegistrationMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        btnForgotPassword=findViewById(R.id.btnForgotPassword)
        btnDisplayUsers = findViewById(R.id.btnDisplayUsers)
        tvRegistrationMessage = findViewById(R.id.tvRegistrationMessage)

        btnRegister.setOnClickListener {
            registerUser()
        }


        btnLogin.setOnClickListener {
//loginUser()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        btnDisplayUsers.setOnClickListener {
            val intent = Intent(this, DisplayUsersActivity::class.java)
            startActivity(intent)
        }
    }


    private fun registerUser() {
        val username = edtUsername.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "http://192.168.100.239/UserApi/register.php" // Corrected URL

        val params = HashMap<String, String>()
        params["username"] = username
        params["email"] = email
        params["password"] = password

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("Registration", "Response: $response")
                tvRegistrationMessage.text = " $response"
            },
            { error ->
                Log.d("Registration", "Response: $error")
                tvRegistrationMessage.text = "Error: ${error.message}"
            }
        ) {
            override fun getParams(): Map<String, String> {
                return params
            }
        }

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }


}
