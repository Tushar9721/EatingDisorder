package com.example.eatingdisorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val chatList: ArrayList<MessageDataClass>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatViewHolder, position: Int) {
        val currentItem = chatList[position]
        if (currentItem.user) {
            holder.consultantMessage.visibility = View.INVISIBLE
            holder.userMessage.visibility = View.VISIBLE
            holder.userMessage.text = currentItem.message.toString()
        } else {
            holder.consultantMessage.visibility = View.VISIBLE
            holder.userMessage.visibility = View.INVISIBLE
            holder.consultantMessage.text = currentItem.message.toString()
        }

    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMessage: TextView = itemView.findViewById(R.id.tv_message)
        val consultantMessage: TextView = itemView.findViewById(R.id.tv_consult_message)


        init {

        }


    }


}