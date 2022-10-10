package com.example.Quize_App

data class question_model(
     val id : Int,
     val question : String,
     val option1: String,
     val option2: String,
     val option3: String,
     val option4: String,
     val ans : Int
)
