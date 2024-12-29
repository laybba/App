package com.example.phpsql

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        // Check only email and password
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "http://192.168.100.239/UserApi/login.php" // Replace with your actual API URL

        val params = HashMap<String, String>()
        params["email"] = email
        params["password"] = password

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                if (response.contains("Login successful")) {
                    // Navigate to HomeActivity and pass email as intent extra
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish() // Close this activity
                } else {
                    Toast.makeText(this, "Login Failed: $response", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): Map<String, String> {
                return params
            }
        }

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
