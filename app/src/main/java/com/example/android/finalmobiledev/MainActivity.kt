package com.example.android.finalmobiledev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        button_login.setOnClickListener {
            when (Pair(edit_username.text.toString() == "", edit_password.text.toString() == "")) {
                Pair(first = true, second = true) -> Toast.makeText(this, "Username and password fields are empty", Toast.LENGTH_SHORT).show()
                Pair(first = true, second = false) -> Toast.makeText(this, "Username field is empty", Toast.LENGTH_SHORT).show()
                Pair(first = false, second = true) -> Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show()
                else -> {
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra("username", edit_username.text.toString())
                    startActivity(intent)
                }
            }
        }
    }
}
