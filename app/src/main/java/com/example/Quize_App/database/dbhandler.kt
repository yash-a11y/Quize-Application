package com.example.Quize_App.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.Quize_App.question_model

class dbhandler(context: Context) : SQLiteOpenHelper(
    context,dbname,null,dbversion
) {
    companion object{
        private const val dbname = "quize"
        private const val dbversion = 1
        private const val table_name = "questions"

        private const val key_num = "id"
        private const val key_q = "question"
        private const val key_op1 = "option1"
        private const val key_op2 = "option2"
        private const val key_op3 = "option3"
        private const val key_op4 = "option4"
        private const val key_ans = "answer"
    }
    override fun onCreate(p0: SQLiteDatabase?) {

        val createtb = ("CREATE TABLE "+ table_name+ " (" +
                key_num + " INTEGER PRIMARY KEY,"
                + key_q + " TEXT,"
                + key_op1 + " TEXT,"
                + key_op2 + " TEXT,"
                + key_op3 + " TEXT,"
                + key_op4 + " TEXT,"
                + key_ans + " TEXT)"
                )

        p0!!.execSQL(createtb)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $table_name")
        onCreate(p0)
    }

    fun addquestion(datamodel : question_model): Long {
        val db = this.writableDatabase

        val content = ContentValues()

        content.put(key_num,datamodel.id)
        content.put(key_q,datamodel.question)
        content.put(key_op1,datamodel.option1)
        content.put(key_op2,datamodel.option2)
        content.put(key_op3,datamodel.option3)
        content.put(key_op4,datamodel.option4)
        content.put(key_ans,datamodel.ans)

        val result = db.insert(table_name,null,content)
        db.close()
        return result

    }

    fun deleteallQ()
    {
        val deleteq = "DELETE FROM $table_name"
        val db = this.writableDatabase
        db.execSQL(deleteq)
    }
    fun getquestion() : ArrayList<question_model>
    {
        val qlist = ArrayList<question_model>()
        val q = "SELECT * FROM $table_name"

        val db = this.writableDatabase

        try {

            val cursor = db.rawQuery(q,null)

            if(cursor.moveToFirst())
            {
                do {
                    val question = question_model(
                    cursor.getInt(cursor.getColumnIndex(key_num)),
                        cursor.getString(cursor.getColumnIndex(key_q)),
                        cursor.getString(cursor.getColumnIndex(key_op1)),
                        cursor.getString(cursor.getColumnIndex(key_op2)),
                        cursor.getString(cursor.getColumnIndex(key_op3)),
                        cursor.getString(cursor.getColumnIndex(key_op4)),
                        cursor.getInt(cursor.getColumnIndex(key_ans))
                    )

                    qlist.add(question)
                }
                    while (cursor.moveToNext())
            }

        }
        catch(e : SQLiteException)
        {
                e.printStackTrace()
                return qlist

        }

        return qlist

    }

}