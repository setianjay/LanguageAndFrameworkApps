package com.setianjay.languageandframeworkapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        moveToMain()
    }

    private fun moveToMain(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }, 2000L)
    }
}