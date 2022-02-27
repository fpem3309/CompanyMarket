package com.example.companymarket

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.databinding.ActivityChatBinding
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.ArrayList
import android.app.Activity

import android.content.Intent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity(){

    private var chatBinding: ActivityChatBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    private var arrayList: ArrayList<Chat>? = null
    private val chatAdapter =  ChatAdapter()

    private val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(chatBinding!!.root)
        chatBinding!!.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        var uid = user?.uid
        //chatBinding.recyclerView.adapter = chatAdapter // setAdapter
        //chatAdapter.addData()

        var chatUid = intent.getStringExtra("Chat_Uid")

        arrayList = ArrayList()
        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        databaseReference = database!!.getReference("Chatroom").child(chatUid!!) // 파이어베이스 Chatroom 테이블의 chatUid와 값이 같은 것 연결


//        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                arrayList!!.clear() // 배열리스트 초기화
//                for(snapshot1 in snapshot.children){
//                    val other = snapshot1.getValue(Chat::class.java)
//                    arrayList!!.add(other!!)
//                    Log.d("chat_data", arrayList.toString())
//
//                    chatAdapter.getChatAdapter(arrayList!!)
//                    chatBinding!!.recyclerView.adapter = chatAdapter //set
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("chat_error", error.toException().toString())
//            }
//        })

        chatBinding!!.recyclerView.adapter = chatAdapter //set

        databaseReference!!.addChildEventListener(object : ChildEventListener{
            //새로 추가된 것만 줌
            //ValueListener는 하나의 값만 바뀌어도 처음부터 다시 값을 줌
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                //새로 추가된 데이터(값 Chat 객체) 가져오기
                val chat: Chat? = snapshot.getValue(Chat::class.java)
                // 그 chat값을 arrayList에 추가
                arrayList!!.add(chat!!)
                chatAdapter.getChatAdapter(arrayList!!)
                Log.d("chat_data", chat.toString())

                // recyclerview 갱신
                chatAdapter.notifyDataSetChanged()
                //메세지 보낼 시 화면을 맨 밑으로 내림
                chatBinding!!.recyclerView.scrollToPosition(arrayList!!.size-1)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("chat_error", error.toException().toString())
            }
        })


        chatBinding!!.imageButton.setOnClickListener{
            var uid = user?.uid
            var edtChat = chatBinding!!.edtChat.text
            var time : Long = System.currentTimeMillis() // ms로 반환
            var timeformat = SimpleDateFormat("HH:mm")
            var timeChat = timeformat.format(time)

            val Chating = Chat("$uid","$edtChat","$timeChat")
            Log.d("sendclick", Chating.toString())
            databaseReference!!.push().setValue(Chating)
            chatBinding!!.edtChat.setText("")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        chatBinding = null
    }

}