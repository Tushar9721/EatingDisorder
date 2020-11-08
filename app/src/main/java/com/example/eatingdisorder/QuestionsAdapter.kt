package com.example.eatingdisorder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private var scoreValue: Int = 0

class QuestionsAdapter(
    private val questionList: ArrayList<QuestionDataClass>,
    private val onResultChangeListener: OnResultChangeListener
) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.questions_layout, parent, false)
        return QuestionViewHolder(itemView)
    }


    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = questionList[position]
        holder.questionText.text = "" + (position + 1) + ". " + currentItem.question
        holder.option1.text = currentItem.Option1
        holder.option2.text = currentItem.Option2

    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questions_name)
        val option1: TextView = itemView.findViewById(R.id.opt1)
        val option2: TextView = itemView.findViewById(R.id.opt2)


        init {
            option1.setOnClickListener {
                option1.setTextColor(Color.GREEN)
                option2.setTextColor(Color.WHITE)
                scoreValue += 1
                onResultChangeListener.onResult(scoreValue)
            }
            option2.setOnClickListener {
                option2.setTextColor(Color.GREEN)
                option1.setTextColor(Color.WHITE)
            }
        }


    }

}