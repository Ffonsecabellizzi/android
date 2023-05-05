package com.example.wallapop

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var termsTextView: TextView
    private lateinit var privacyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        termsTextView = findViewById(R.id.termsTextView)
        privacyTextView = findViewById(R.id.privacyTextView)

        emailEditText.addTextChangedListener(loginTextWatcher)
        passwordEditText.addTextChangedListener(loginTextWatcher)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val intent = Intent(this, ThirdActivity::class.java).apply {
                putExtra("email", email)
            }
            startActivity(intent)
        }

        termsTextView.setOnClickListener {
            openUrl("http://www.google.com")
        }

        privacyTextView.setOnClickListener {
            openUrl("https://www.twitch.tv")
        }
    }

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // No es necesario implementar este método
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailInput = emailEditText.text.toString().trim()
            val passwordInput = passwordEditText.text.toString().trim()

            loginButton.isEnabled = isEmailValid(emailInput) && isPasswordValid(passwordInput)
        }

        override fun afterTextChanged(s: Editable) {
            // No es necesario implementar este método
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}")
        return passwordPattern.matcher(password).matches()
    }
}