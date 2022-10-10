package com.example.Quize_App.activities

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.Quize_App.R
import com.example.Quize_App.database.dbhandler
import com.example.Quize_App.databinding.NoActiveTestBinding
import com.example.Quize_App.question_model
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val n: String?=null
    private var sdpf : SharedPreferences ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sdpf = this.getSharedPreferences("PREF", MODE_PRIVATE)



    b1.setOnClickListener {
        var n = name.text.toString()
        if(n.isEmpty())
        {
            Toast.makeText(this,"enter vailid name",Toast.LENGTH_SHORT).show()
        }

        else
        {
            when
            {
                getqlist().isEmpty() ->
                {
                    val dialog = Dialog(this)
                    val dialog_view : NoActiveTestBinding = NoActiveTestBinding.inflate(layoutInflater)

                    dialog.setContentView(dialog_view.root)
                    dialog_view.btnExit.setOnClickListener {
                        finishAffinity()
                    }
                    dialog.show()


                }
                else ->
                {
                    n = name.text.toString()
                    sdpf?.edit()!!.putString("name",n).commit()



                    AlertDialog.Builder(this).
                    setMessage("are you sure about start quize")
                        .setPositiveButton("Yes"){
                                _,_, ->
                            val intent = Intent(this, Quetion_activity::class.java)
                            startActivity(intent)
                            finish()

                        }
                        .setNegativeButton("No")
                        {
                                dialog,_ ->
                            dialog.dismiss()
                        }.show()


                }
            }







        }
    }


    }


    private fun getqlist(): ArrayList<question_model> {
        val db = dbhandler(this)

        val qlist = db.getquestion()


        return qlist

    }



}