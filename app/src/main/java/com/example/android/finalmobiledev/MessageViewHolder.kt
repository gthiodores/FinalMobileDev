package com.example.android.finalmobiledev

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val username : TextView = itemView.findViewById(R.id.username_text)
    private val date : TextView = itemView.findViewById(R.id.date_text)
    private val time : TextView = itemView.findViewById(R.id.time_text)
    private val message : TextView = itemView.findViewById(R.id.message_text)

    fun setMessage(message : ChatMessage) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        this.username.text = message.username
        this.date.text = dateFormat.format(Date(message.timestamp)).toString()
        this.time.text = timeFormat.format(Date(message.timestamp)).toString()
        this.message.text =  message.contents
    }
}