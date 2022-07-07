package com.summer.practice.tvtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val emailField = findViewById<EditText>(R.id.emailField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val errorMessage = findViewById<TextView>(R.id.errorMessage)
        val loginButton = findViewById<Button>(R.id.LoginButton)
        val linkToRegistration = findViewById<TextView>(R.id.linkToRegistration)

        loginButton.setOnClickListener {
            if (emailField.text.isEmpty() or passwordField.text.isEmpty()) {
                errorMessage.text = getString(R.string.fill_all_fields)
                return@setOnClickListener
            }

            //TODO authentication

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        linkToRegistration.setOnClickListener {
            val intent = Intent() //TODO change () for (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}