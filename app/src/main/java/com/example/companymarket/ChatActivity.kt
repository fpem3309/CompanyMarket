package com.example.companymarket

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.databinding.ActivityChatBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
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

        var edtChat = chatBinding.edtChat.text

        var time : Long = System.currentTimeMillis() // ms로 반환
        var timeformat = SimpleDateFormat("HH:mm")
        var timeChat = timeformat.format(time)

        chatBinding.recyclerView.adapter = chatAdapter // setAdapter
        chatAdapter.addData()


        chatBinding.imageButton.setOnClickListener{
            val chat = Chat("1","$edtChat","$timeChat")
            Log.d("sendclick", chat.toString())
        }
    }


}