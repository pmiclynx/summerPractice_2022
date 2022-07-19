package com.summer.practice.tvtracker.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.summer.practice.tvtracker.MainActivity
import com.summer.practice.tvtracker.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerButton.setOnClickListener {
            if (binding.password.text.isEmpty() or binding.verifyPassword.text.isEmpty()
                or binding.username.text.isEmpty() or binding.emailAddress.text.isEmpty()) {
                val text = "Please fill all fields!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            if(!(binding.password.getText().toString().equals(binding.verifyPassword.getText().toString()))){
                val text = "Passwords aren't matching!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return@setOnClickListener
            }
            val emailText=binding.emailAddress.getText().toString()
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
            finish()
        }

    }

}