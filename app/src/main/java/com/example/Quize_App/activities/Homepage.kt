package com.example.Quize_App.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.Quize_App.R
import com.example.Quize_App.databinding.DialogverfBinding
import kotlinx.android.synthetic.main.activity_homepage.*

class homepage : AppCompatActivity() {
    private val securitycode = "oky1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        t_btn.setOnClickListener {

            val dialog = Dialog(this)

            val dialog_view : DialogverfBinding = DialogverfBinding.inflate(layoutInflater)
//            val dialog_view : DialogverfBinding = DialogverfBinding.inflate(layoutInflater)

            dialog.setContentView(dialog_view.root)

            dialog.setCanceledOnTouchOutside(true)

            dialog_view.vrfyBtn.setOnClickListener {

                if(dialog_view.vrfycTv.text.isNullOrEmpty())
                {
                    Toast.makeText(this@homepage,"required",Toast.LENGTH_SHORT).show()
                }
                else{

                    if(dialog_view.vrfycTv.text.toString().equals(securitycode))
                    {
                                    val intent = Intent(this@homepage, TeacherActivity::class.java)
                                    startActivity(intent)
                                    dialog.dismiss()
                    }
                    else{
                        Log.i("oky","not oky")
                        Toast.makeText(this@homepage,"! enter valid code",Toast.LENGTH_SHORT).show()

                    }
                }
            }

            dialog.show()




        }

        s_btn.setOnClickListener {
            val intent = Intent(this@homepage, MainActivity::class.java)
            startActivity(intent)
        }
    }
}