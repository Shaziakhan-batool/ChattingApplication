package com.example.chattingapplication.sharedpreferences


import com.example.chattingapplication.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapplication.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load animation (fade + zoom)
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_zoom)
        binding.logoImage.startAnimation(anim)
        binding.appName.startAnimation(anim)

        // After 3 sec go to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SplashScreenActivity::class.java))
            finish()
        }, 4000)
    }
}
