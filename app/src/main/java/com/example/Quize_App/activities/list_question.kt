package com.example.Quize_App.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Quize_App.R
import com.example.Quize_App.adapter.adapter
import com.example.Quize_App.database.dbhandler
import kotlinx.android.synthetic.main.activity_list_question.*

class list_question : AppCompatActivity() {

    private var db : dbhandler ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_question)

        setSupportActionBar(tblr_qlit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tblr_qlit.setNavigationOnClickListener {
            onBackPressed()
        }

         db = dbhandler(this)
         rcyclervi.layoutManager = LinearLayoutManager(this)
         getalldata()


        allqdlt_btn.setOnClickListener {
            deleteAll()
            getalldata()
        }

    }

    private fun getalldata()
    {
        val qlist = db!!.getquestion()
        val adapter = adapter(qlist)

        rcyclervi.adapter = adapter
    }
    private fun deleteAll()
    {

            AlertDialog.Builder(this)
                .setMessage("Are sure about delete Questions !")
                .setPositiveButton("YES")
                {
                    _,_ ->
                    db!!.deleteallQ()
                    Toast.makeText(this@list_question,"questions deleted",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("NO")
                {
                    dialog,_, ->

                    dialog.dismiss()
                }
                .show()


    }
}