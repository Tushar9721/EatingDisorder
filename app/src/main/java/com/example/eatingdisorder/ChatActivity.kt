package com.example.eatingdisorder

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ChatActivity : AppCompatActivity() {

    private val messagesList: ArrayList<MessageDataClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val recycler_view = findViewById<RecyclerView>(R.id.rv_messages)

        //adding data..
        addingData()

        //onClick
        onClick()

        //setting adapter
        recycler_view.adapter = ChatAdapter(messagesList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)


    }

    private fun onClick() {

        val chatBack = findViewById<TextView>(R.id.chatBack)
        chatBack.setOnClickListener {
            onBackPressed()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_rigth
            )
        }

        val et_message = findViewById<EditText>(R.id.et_message)
        val btn_send = findViewById<ImageView>(R.id.btn_send)

        btn_send.setOnClickListener {
            if (et_message.text.toString().isEmpty()) {
                Snackbar.make(btn_send, "Please type the message!!", Snackbar.LENGTH_SHORT).show()
            } else {
                message(et_message.text.toString(), true)
                et_message.setText(" ")
            }

        }

    }

    private fun message(mess: String, user: Boolean) {
        messagesList.add(MessageDataClass(mess, user))
    }

    private fun addingData() {
        message("Hello! we are here to help you and come out of disorder.", false)
        message("Hello! Yes its really disturbing.", true)
    }
}