package com.example.Quize_App.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.Quize_App.R
import com.example.Quize_App.database.dbhandler
import com.example.Quize_App.databinding.NoActiveTestBinding
import com.example.Quize_App.question_model
import kotlinx.android.synthetic.main.activity_quition.*
import kotlin.collections.ArrayList

class Quetion_activity : AppCompatActivity(), View.OnClickListener {

    private var optionp : Int  = 0
    private var maxprg_question : Int = 0
    private var ans : Int? = 0
    private var result: Int? = 0
    private var candidate : String? = null
    private var qposition : Int = 1
    private var quetionlist : ArrayList<question_model> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quition)


            quetionlist = getqlist()
            if(quetionlist == null)
            {
               val dialog = Dialog(this)

                val dialog_view : NoActiveTestBinding = NoActiveTestBinding.inflate(layoutInflater)

                dialog.setContentView(dialog_view.root)

                dialog.show()
            }
            else {
                setquestion()


                in_op1.setOnClickListener(this)
                in_op2.setOnClickListener(this)
                in_op3.setOnClickListener(this)
                in_op4.setOnClickListener(this)
                submit.setOnClickListener(this)
            }



    }



    fun setquestion()
    {
        setoptionview()

        val prog = qposition
        val question : question_model? = quetionlist?.get(qposition-1)

        number.text = question!!.id.toString()
        prg.progress = prog

        count.text = "${prog}"+ "/" +"$maxprg_question"


        in_q.text = question!!.question.toString()
        in_op1.text = question!!.option1.toString()
        in_op2.text = question!!.option2.toString()
        in_op3.text = question!!.option3.toString()
        in_op4.text = question!!.option4.toString()

    }

    private fun setoptionview()
    {
        val tmp = ArrayList<TextView>()
        tmp.add(0,in_op1)
        tmp.add(1,in_op2)
        tmp.add(2,in_op3)
        tmp.add(3,in_op4)

        for(option in tmp)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.option_layout)
        }
    }

    private fun selectoption(v:TextView,selectop : Int)
    {
                  setoptionview()
                  optionp = selectop
                  v.setTextColor(Color.parseColor("#000000"))
                  v.typeface = Typeface.DEFAULT_BOLD
                  v.background = ContextCompat.getDrawable(this, R.drawable.optionselect)
    }

    override fun onClick(v: View?) {

        setoptionview()

        when(v?.id)
        {
            R.id.in_op1 -> {
                selectoption(in_op1,1)
            }
            R.id.in_op2 -> selectoption(in_op2,2)
            R.id.in_op3 -> selectoption(in_op3,3)
            R.id.in_op4 -> selectoption(in_op4,4)
            R.id.submit ->
            {
                if(optionp == 0)
                {
                    qposition++

                    when
                    {
                        qposition <= quetionlist!!.size ->
                        {
                            setquestion()
                        }
                        else ->
                        {
                            Toast.makeText(this,"QUIZE OVER",Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, Result_activity::class.java)
                            intent.putExtra("getresult",result)
                            startActivity(intent)
                        }
                    }
                }

                else
                {
                    val tmp = quetionlist?.get(qposition-1)

                    if(tmp!!.ans != optionp)
                    {
                     ansview(optionp, R.drawable.wrong)
                    }
                    else
                    {
                        result = result?.plus(1)
                    }
                    ansview(tmp.ans, R.drawable.correct)

                    if(qposition == quetionlist!!.size)
                    {
                        submit.text = "FINISH"
                    }
                    else
                    {
                        submit.text = "GO TO NEXT QUESTION"
                    }

                    optionp = 0
                }
            }
        }
    }


    private fun ansview(ans : Int,drawable: Int)
    {
        when(ans)
        {
            1-> in_op1.background = ContextCompat.getDrawable(this,drawable)
            2-> in_op2.background = ContextCompat.getDrawable(this,drawable)
            3-> in_op3.background = ContextCompat.getDrawable(this,drawable)
            4-> in_op4.background = ContextCompat.getDrawable(this,drawable)
        }
    }



    //get questions from database

    private fun getqlist(): ArrayList<question_model> {
        val db = dbhandler(this)

        val qlist = db.getquestion()
        maxprg_question = qlist.size

        return qlist

    }

}



