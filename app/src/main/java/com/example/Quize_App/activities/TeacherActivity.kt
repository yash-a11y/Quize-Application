package com.example.Quize_App.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Quize_App.R
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        add_q.setOnClickListener {

            val intent = Intent(this@TeacherActivity,add_question::class.java)
            startActivity(intent)
        }

        list_q.setOnClickListener {

            val intent = Intent(this@TeacherActivity,list_question::class.java)
            startActivity(intent)
        }
    }
}