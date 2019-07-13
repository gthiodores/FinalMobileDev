package com.example.android.finalmobiledev

data class ChatMessage(val username : String = "", val timestamp : Long = 0,
                        val contents : String = "", val url : String = "") {

    //Required empty constructor for firebase
    constructor() : this ("")
}