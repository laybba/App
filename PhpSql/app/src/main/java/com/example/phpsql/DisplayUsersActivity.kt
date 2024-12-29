package com.example.phpsql

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DisplayUsersActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var btnFetchUser: Button
    private lateinit var tvUserData: TextView  // Reference to TextView for displaying user data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_users)

        edtEmail = findViewById(R.id.edtEmail)
        btnFetchUser = findViewById(R.id.btnFetchUser)
        tvUserData = findViewById(R.id.tvUserData)  // Initialize the TextView

        btnFetchUser.setOnClickListener {
            fetchUserData()
        }
    }

    private fun fetchUserData() {
        val email = edtEmail.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "http://192.168.100.239/UserApi/DisplayUsersData.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                try {
                    // Log the original response for debugging
                    Log.d("DisplayUserActivity", "Original Response: $response")

                    // Try to parse the response as JSONArray first
                    val jsonArray = try {
                        JSONArray(response) // Check if it's an array
                    } catch (e: JSONException) {
                        null // Not a JSONArray, so we catch the exception and proceed to JSONObject
                    }

                    // If it's a JSONArray, try to get the first object inside
                    if (jsonArray != null && jsonArray.length() > 0) {
                        val jsonObject = jsonArray.getJSONObject(0)  // Get the first object from the array
                        val status = jsonObject.optString("status")
                        val message = jsonObject.optString("message")

                        if (status == "success") {
                            val dataObject = jsonObject.optJSONObject("data")  // Get 'data' object

                            if (dataObject != null) {
                                val username = dataObject.optString("username", "N/A")
                                val email = dataObject.optString("email", "N/A")

                                // Display the user data in the TextView
                                tvUserData.text = "Username: $username\nEmail: $email"
                            } else {
                                tvUserData.text = "No user data found"
                            }
                        } else {
                            tvUserData.text = message
                        }
                    } else {
                        // If it's not a JSONArray, parse it as JSONObject
                        val jsonObject = JSONObject(response)
                        val status = jsonObject.optString("status")
                        val message = jsonObject.optString("message")

                        if (status == "success") {
                            val dataObject = jsonObject.optJSONObject("data")

                            if (dataObject != null) {
                                val username = dataObject.optString("username", "N/A")
                                val email = dataObject.optString("email", "N/A")

                                // Display the user data in the TextView
                                tvUserData.text = "Username: $username\nEmail: $email"
                            } else {
                                tvUserData.text = "No user data found"
                            }
                        } else {
                            tvUserData.text = message
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Parsing Error", e.message.toString())
                    tvUserData.text = "Parsing error: ${e.message}"
                }
            },
            { error ->
                tvUserData.text = "Error: ${error.message}" // Show the error in the TextView
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                return params
            }
        }

        Volley.newRequestQueue(this).add(stringRequest)
    }
}
