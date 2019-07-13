package com.example.android.finalmobiledev

import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class Users(val username : String) {

    fun sendChat(chatContent : String) {
        val chatId = Calendar.getInstance().timeInMillis
        val dbReference = FirebaseDatabase.getInstance().reference
        dbReference.root
            .child("messages")
            .push()
            .setValue(ChatMessage(username, chatId, chatContent))
    }

    fun sendImage(url : String) {
        val chatId = Calendar.getInstance().timeInMillis
        val dbReference = FirebaseDatabase.getInstance().reference
        dbReference.root
            .child("messages")
            .push()
            .setValue(ChatMessage(username, chatId, url = url))
    }
}