package com.summer.practice.tvtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.summer.practice.tvtracker.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide();

        Handler().postDelayed({
            val intent= Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}