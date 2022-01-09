package com.example.tasktimerappgroup4.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.LaunchActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, LaunchActivity::class.java))
        }, 2000)
    }
}