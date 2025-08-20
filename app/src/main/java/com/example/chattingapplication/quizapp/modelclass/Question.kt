package com.example.chattingapplication.quizapp.modelclass


data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

