package com.example.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phpsql.Adapters.User
import com.example.phpsql.R


data class User(val username: String, val email: String)

class UserAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.tvUsername.text = user.username
        holder.tvEmail.text = user.email
    }

    override fun getItemCount() = users.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }
}


