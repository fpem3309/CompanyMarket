package com.example.companymarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.databinding.ActivityChatBinding
import java.util.ArrayList

class ChatActivity : AppCompatActivity(){

    private val chatAdapter =  ChatAdapter()

    private lateinit var chatBinding: ActivityChatBinding
    private var arrayList: ArrayList<Chat>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(chatBinding.root)
        chatBinding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        chatBinding.recyclerView.adapter = chatAdapter // setAdapter
        chatAdapter.addData()
    }

}