package com.summer.practice.tvtracker.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.summer.practice.tvtracker.MainActivity
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.ActivityLoginBinding
import com.summer.practice.tvtracker.databinding.ActivityRegistrationBinding
import com.summer.practice.tvtracker.login.LoginActivity

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerButton.setOnClickListener {
            if (binding.Password.text.isEmpty() or binding.verifyPassword.text.isEmpty()
                or binding.username.text.isEmpty() or binding.EmailAddress.text.isEmpty()) {
                val text = "Please fill all fields!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            if(!(binding.Password.getText().toString().equals(binding.verifyPassword.getText().toString()))){
                val text = "Passwords aren't matching!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            val emailText=binding.EmailAddress.getText().toString()
            if(!(Patterns.EMAIL_ADDRESS.matcher(emailText).matches())){
                val text = "Email invalid"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            //TODO authentication

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

}