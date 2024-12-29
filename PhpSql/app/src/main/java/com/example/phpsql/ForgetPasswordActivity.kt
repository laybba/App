package com.example.phpsql

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtNewPassword: EditText
    lateinit var btnResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        edtEmail = findViewById(R.id.edtEmail)
        edtNewPassword = findViewById(R.id.edtNewPassword)
        btnResetPassword = findViewById(R.id.btnResetPassword)

        btnResetPassword.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = edtEmail.text.toString().trim()
        val newPassword = edtNewPassword.text.toString().trim()

        if (email.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "http://192.168.100.239/UserApi/forgetpassword.php" // Update with your server's URL

        val params = HashMap<String, String>()
        params["email"] = email
        params["new_password"] = newPassword

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                if (response.contains("Password updated successfully")) {
                    Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show()
                    finish() // Close this activity
                } else {
                    Toast.makeText(this, "Error: $response", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
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
