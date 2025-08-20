package com.example.chattingapplication.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapplication.R
import com.google.android.material.card.MaterialCardView

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var option1: MaterialCardView
    private lateinit var option2: MaterialCardView
    private lateinit var option3: MaterialCardView
    private lateinit var option4: MaterialCardView
    private lateinit var btnNext: Button
    private lateinit var progressBar: ProgressBar

    private var currentQuestionIndex = 0
    private var score = 0
    private var answered = false

    // ✅ 10 Kotlin Questions
    private val questions = listOf(
        "Which keyword is used to create a class in Kotlin?",
        "Which function is the entry point of a Kotlin program?",
        "Which symbol is used for string interpolation?",
        "What is the default visibility modifier in Kotlin?",
        "Which keyword is used to declare a variable that can be reassigned?",
        "Which collection type is immutable in Kotlin?",
        "Which function is used to print output in Kotlin?",
        "Which keyword is used to create an object of a class?",
        "Which type represents a null-safe variable in Kotlin?",
        "Which feature of Kotlin helps to remove null pointer exceptions?"
    )

    // ✅ Options for each question
    private val options = listOf(
        listOf("fun", "class", "def", "obj"),
        listOf("start()", "main()", "run()", "execute()"),
        listOf("$", "#", "&", "%"),
        listOf("public", "private", "protected", "internal"),
        listOf("val", "var", "let", "const"),
        listOf("ArrayList", "MutableList", "List", "HashMap"),
        listOf("echo()", "print()", "printf()", "console.log()"),
        listOf("object", "new", "create", "make"),
        listOf("String?", "Nullable<String>", "Safe<String>", "Optional<String>"),
        listOf("Smart Casts", "Type Casting", "Generics", "Null Safety")
    )

    // ✅ Correct answer indexes for each question
    private val correctAnswers = listOf(
        1, // class
        1, // main()
        0, // $
        0, // public
        1, // var
        2, // List
        1, // print()
        0, // object
        0, // String?
        3  // Null Safety
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestion = findViewById(R.id.tvQuestion)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        btnNext = findViewById(R.id.btnNext)
        progressBar = findViewById(R.id.progressBar)

        progressBar.max = questions.size

        loadQuestion()

        val optionViews = listOf(option1, option2, option3, option4)

        optionViews.forEachIndexed { index, card ->
            card.setOnClickListener {
                if (!answered) {
                    checkAnswer(index, card)
                }
            }
        }

        btnNext.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                resetOptions()
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        tvQuestion.text = questions[currentQuestionIndex]
        option1.findViewById<TextView>(R.id.tvOption1).text = options[currentQuestionIndex][0]
        option2.findViewById<TextView>(R.id.tvOption2).text = options[currentQuestionIndex][1]
        option3.findViewById<TextView>(R.id.tvOption3).text = options[currentQuestionIndex][2]
        option4.findViewById<TextView>(R.id.tvOption4).text = options[currentQuestionIndex][3]

        progressBar.progress = currentQuestionIndex + 1
        answered = false
    }

    private fun checkAnswer(selectedIndex: Int, selectedCard: MaterialCardView) {
        val correctIndex = correctAnswers[currentQuestionIndex]
        answered = true

        if (selectedIndex == correctIndex) {
            score++
            selectedCard.strokeColor = Color.parseColor("#27AE60") // green
        } else {
            selectedCard.strokeColor = Color.parseColor("#E74C3C") // red
            val optionViews = listOf(option1, option2, option3, option4)
            optionViews[correctIndex].strokeColor = Color.parseColor("#27AE60") // green for correct
        }
    }

    private fun resetOptions() {
        val optionViews = listOf(option1, option2, option3, option4)
        optionViews.forEach { it.strokeColor = Color.DKGRAY }
    }
}
