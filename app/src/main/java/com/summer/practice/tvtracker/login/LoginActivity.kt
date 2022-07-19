package com.summer.practice.tvtracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.summer.practice.tvtracker.MainActivity
import com.summer.practice.tvtracker.registration.RegistrationActivity
import com.summer.practice.tvtracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginButton.setOnClickListener {
            if (binding.emailField.text.isEmpty() or binding.passwordField.text.isEmpty()) {
                val text = "Please fill all fields!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            val emailText=binding.emailField.getText().toString()
            if(!(Patterns.EMAIL_ADDRESS.matcher(emailText).matches())){
                val text = "Email invalid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            //TODO authentication

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.linkToRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}