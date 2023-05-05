package com.example.wallapop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val emailTextView: TextView = findViewById(R.id.emailTextView)
        val email = intent.getStringExtra("email")
        emailTextView.text = email
    }
}