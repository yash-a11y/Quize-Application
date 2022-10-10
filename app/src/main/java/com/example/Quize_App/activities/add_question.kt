package com.example.Quize_App.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.Quize_App.R
import com.example.Quize_App.database.dbhandler
import com.example.Quize_App.question_model
import kotlinx.android.synthetic.main.activity_add_question.*

class add_question : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        setSupportActionBar(add_tlbr)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        add_tlbr.setNavigationOnClickListener {
            onBackPressed()
        }
        add_btn.setOnClickListener {

            when{
                number_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }
                question_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }

                op1_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }

                op2_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }

                op3_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }

                op4_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }

                ans_tv.text.isNullOrEmpty() ->
                {
                    Toast.makeText(this@add_question,"required",Toast.LENGTH_SHORT).show()
                }
                else ->
                {
                    val question = question_model(
                        number_tv.text.toString().toInt(),
                        question_tv.text.toString(),
                        op1_tv.text.toString(),
                        op2_tv.text.toString(),
                        op3_tv.text.toString(),
                        op4_tv.text.toString(),
                        ans_tv.text.toString().toInt()
                    )

                    val db = dbhandler(this)

                    val result = db.addquestion(question)

                    if(result > 0)
                    {
                        Toast.makeText(this@add_question,"question add",Toast.LENGTH_SHORT).show()
                        restView()
                        Log.i("add","done")
                    }

                }
            }

        }


    }

    private fun restView()
    {
        number_tv.text = null
        question_tv.text = null
        op1_tv.text = null
        op2_tv.text = null
        op3_tv.text = null
        op4_tv.text = null
        ans_tv.text = null
    }


}