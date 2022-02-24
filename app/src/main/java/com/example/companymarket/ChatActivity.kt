package com.example.companymarket

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.databinding.ActivityChatBinding
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.ArrayList

class ChatActivity : AppCompatActivity(){

    private var chatBinding: ActivityChatBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var arrayList: ArrayList<Chat>? = null
    private val chatAdapter =  ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(chatBinding!!.root)
        chatBinding!!.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        var edtChat = chatBinding!!.edtChat.text

        var time : Long = System.currentTimeMillis() // ms로 반환
        var timeformat = SimpleDateFormat("HH:mm")
        var timeChat = timeformat.format(time)

        //chatBinding.recyclerView.adapter = chatAdapter // setAdapter
        //chatAdapter.addData()

        arrayList = ArrayList()
        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        databaseReference = database!!.getReference("Other") // 파이어베이스 Other 테이블 연결

        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList!!.clear() // 배열리스트 초기화
                for(snapshot1 in snapshot.children){
                    val other = snapshot1.getValue(Chat::class.java)
                    arrayList!!.add(other!!)
                    Log.d("chat_data", arrayList.toString())

                    chatAdapter.getChatAdapter(arrayList!!)
                    chatBinding!!.recyclerView.adapter = chatAdapter //set
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("chat_error", error.toException().toString())
            }
        })

        chatBinding!!.imageButton.setOnClickListener{
            val chating = Chat("1","$edtChat","$timeChat")
            Log.d("sendclick", chating.toString())
            databaseReference!!.push().setValue(Chat("friend","$edtChat","$timeChat"))

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        chatBinding = null
    }
}