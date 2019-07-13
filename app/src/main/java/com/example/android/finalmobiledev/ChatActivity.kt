package com.example.android.finalmobiledev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private lateinit var user : Users
    private val mFirebaseDatabaseReference = FirebaseDatabase.getInstance().reference
    private var mManager : LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //Create a new user object with constructor from intent or anonymous if null
        user = Users(intent.extras?.getString("username") ?: "Anonymous")

        //Send message when user clicks the FAB and the text field isn't empty, display toast otherwise
        send_button.setOnClickListener {
            when(edit_message.text.toString()) {
                "" -> Toast.makeText(this, "No message was found", Toast.LENGTH_SHORT).show()
                else -> {
                    user.sendChat(edit_message.text.toString())
                    edit_message.setText("")
                }
            }
        }

        //Create a new FirebaseRecyclerOption
        val query = mFirebaseDatabaseReference.child("messages")
        val firebaseRecyclerOptions = FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(query, ChatMessage::class.java)
            .setLifecycleOwner(this)
            .build()

        //Initialize recycler view adapter
        val adapter = object : FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder> (firebaseRecyclerOptions) {
            override fun onBindViewHolder(itemView: MessageViewHolder, position: Int, model: ChatMessage) {
                itemView.setMessage(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
                return MessageViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_item, parent, false))
            }
        }

        //Creates a data observer to automatiaclly scroll to the last item in the RecyclerView
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                mManager.smoothScrollToPosition(message_recycler, null, positionStart)
            }
        })

        //Sets RecyclerView LayoutManager and Adapter
        message_recycler.layoutManager = mManager
        message_recycler.adapter = adapter
    }
}
