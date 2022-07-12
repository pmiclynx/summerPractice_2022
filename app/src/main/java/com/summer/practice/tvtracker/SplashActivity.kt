package com.summer.practice.tvtracker;

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.summer.practice.tvtracker.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
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
