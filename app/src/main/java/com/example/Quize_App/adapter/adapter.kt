package com.example.Quize_App.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Quize_App.databinding.ItemviewBinding
import com.example.Quize_App.question_model

class adapter(private val qlist : ArrayList<question_model>) : RecyclerView.Adapter<viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(
            ItemviewBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val question = qlist.get(position)
        holder.number.text = question.id.toString()
        holder.question.text = question.question
        holder.op1.text = "1 : ${question.option1}"
        holder.op2.text = "2 : ${question.option2}"
        holder.op3.text = "3 : ${question.option3}"
        holder.op4.text = "4 : ${question.option4}"
        holder.answare.text = "answer : ${question.ans.toString()}"


    }

    override fun getItemCount(): Int {
        return qlist.size
    }
}

class viewholder(bind : ItemviewBinding) : RecyclerView.ViewHolder(bind.root) {

    val number = bind.tvn
    val question  = bind.tvq
    val op1 = bind.op1tv
    val op2 = bind.op2tv
    val op3 = bind.op3tv
    val op4 = bind.op4tv
    val answare = bind.tvans

}
