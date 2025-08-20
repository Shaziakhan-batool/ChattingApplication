package com.example.chattingapplication.quizapp

import android.content.Intent
import android.os.Bundle
import com.example.chattingapplication.R
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 10)

        val tvFinalScore: TextView = findViewById(com.example.chattingapplication.R.id.tvFinalScore)
        val tvMessage: TextView = findViewById(com.example.chattingapplication.R.id.tvMessage)
        val btnRestart: Button = findViewById(com.example.chattingapplication.R.id.btnRestart)

        tvFinalScore.text = "Your Score: $score/$total"

        tvMessage.text = when {
            score == total -> "Excellent! 🎉"
            score >= total / 2 -> "Well Done!"
            else -> "Keep Practicing!"
        }

        btnRestart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
