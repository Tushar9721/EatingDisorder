package com.example.eatingdisorder

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuestionsActivity : AppCompatActivity(), OnResultChangeListener {

    private val dataListQuestions: ArrayList<QuestionDataClass> = ArrayList()
    private var totalScoreResult: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        val recycler_view = findViewById<RecyclerView>(R.id.rvQuestions)

        //adding the data
        addingData()

        //onclick function
        onClick()

        recycler_view.adapter = QuestionsAdapter(dataListQuestions, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun onClick() {
        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            alertBox(totalScoreResult)
        }

        val questionsBack = findViewById<TextView>(R.id.questionsBack)
        questionsBack.setOnClickListener {
            onBackPressed()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_rigth
            );
        }

    }

    private fun alertBox(value: Int) {
        val dialog = Dialog(this, android.R.style.Theme_Light)
        var decision: String = "not having"
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.confirmation_alert)
        dialog.window!!.setBackgroundDrawableResource(R.color.my_clor);
        dialog.show()

        val checkPreventions = dialog.findViewById<TextView>(R.id.checkPreventions)
        checkPreventions.setOnClickListener {
            openPreventionDialog()
        }

        val crossConfirmation = dialog.findViewById<ImageView>(R.id.crossConfirmation)
        crossConfirmation.setOnClickListener {
            dialog.hide()
            finish()
        }

        if (value >= 6) {
            decision = "having"
        } else if (value >= 4 || value < 6) {
            decision = "developing"
        }

        val textQuestionPop = dialog.findViewById<TextView>(R.id.textQuestionPop)
        textQuestionPop.text =
            "You are " + decision.toString() + " symptoms of eating disorder. Do you Want to talk with consultant?"

        val confirm = dialog.findViewById<TextView>(R.id.confirm)
        confirm.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slie_out_left
            )
            finish()
        }

    }

    private fun openPreventionDialog() {
        val dialog = Dialog(this, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.prevention_dialog)
        dialog.show()

        val preventTextview = dialog.findViewById<TextView>(R.id.preventTextview)
        preventTextview.movementMethod = ScrollingMovementMethod()


        val preClose = dialog.findViewById<ImageView>(R.id.prevention)
        preClose.setOnClickListener {
            dialog.hide()

        }
    }


    private fun questionDataList(
        question: String,
        Option1: String,
        Option2: String
    ) {

        dataListQuestions.add(QuestionDataClass(question, Option1, Option2))
    }

    private fun addingData() {
        questionDataList(
            "Do you prefer to stay alone rather being at social gathering?",
            "Yes", "No"
        )
        questionDataList("Do you experience constant weight fluctuations?", "Yes", "No")
        questionDataList("Are you obsessed with calories and fat contents of food?", "Yes", "No")
        questionDataList(
            "Do you often switch between periods of overeating and fasting?",
            "Yes",
            "No"
        )
        questionDataList("Are you concerned with your body size and shape", "Yes", "No")
        questionDataList("Do you find it difficult to concentrate ?", "Yes", "No")
        questionDataList(
            "Do you prefer to skip meals or take small portions of food at regular meals?",
            "Yes",
            "No"
        )
        questionDataList(
            "Do you  believe in working out is all about weight loss, and missing a workout makes you  upset?",
            "Yes",
            "No"
        )
    }

    override fun onResult(totalScore: Int?) {
        if (totalScore != null) {
            totalScoreResult = totalScore
        }
    }

}
