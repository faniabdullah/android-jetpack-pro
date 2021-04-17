package com.bangkit.faniabdullah_jetpack

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.faniabdullah_jetpack.databinding.ActivitySplashScreenBinding
import com.bangkit.faniabdullah_jetpack.ui.MainActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, Constant.SPLASH_TIME_OUT)
    }
}