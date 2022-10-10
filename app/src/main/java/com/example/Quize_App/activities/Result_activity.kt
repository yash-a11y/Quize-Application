package com.example.Quize_App.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.Quize_App.R
import kotlinx.android.synthetic.main.activity_result.*

class Result_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val sdf : SharedPreferences = this.getSharedPreferences("PREF", MODE_PRIVATE)

        val bundle = intent.extras
        tv_name.text = sdf.getString("name",null)
        if (bundle != null) {
            if(bundle.getInt("getresult") != null)
            {
                val marks = bundle.getInt("getresult")
                tv_score.text = "your marks is ${marks}"
            }

            btn_finish.setOnClickListener{
                AlertDialog.Builder(this)
                    .setMessage("your experience")
                    .setPositiveButton("Feedback")
                    {
                        _,_ ->


                    }
                    .setNegativeButton("exit")
                    {
                        dialog,_ ->
                        dialog.dismiss()
                        finishAffinity()
                    }.show()
            }
//           if(bundle.getString("parti") != null)
//           {
//               val name = bundle.getString("parti")
//               canname.typeface = Typeface.DEFAULT_BOLD
//               canname.text = name.toString()
//           }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}