package com.example.companymarket.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.Chat
import com.example.companymarket.ChatAdapter
import com.example.companymarket.ChatroomAdapter
import com.example.companymarket.Product
import com.example.companymarket.databinding.FragmentChatroomBinding
import com.google.firebase.database.*
import java.util.ArrayList

class ChatroomFragment : Fragment() {
    private var chatroombinding :FragmentChatroomBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var arrayList: ArrayList<Chat>? = null
    private val chatroomAdapter =  ChatroomAdapter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        chatroombinding = FragmentChatroomBinding.inflate(inflater, container, false)
        val view = chatroombinding!!.root
        chatroombinding!!.chatroomRecycler.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onResume() {
        super.onResume()
        arrayList = ArrayList()
        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동

        databaseReference = database!!.getReference("Chatroom").child("a5zLnHLwDWQ2zyDGXWAe3fZJ0xm2").child("test") // 파이어베이스 Chatroom 테이블 연결
        Log.d("Chatroom_db_data", databaseReference.toString())

        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // 파이어 베이스 데이터 받아옴
                // arrayList!!.clear() //배열리스트 초기화

                    val chatroom = snapshot.getValue(Chat::class.java)

                    arrayList!!.add(chatroom!!)

                    Log.d("chatroom_data", arrayList.toString())
                    Log.d("chatroom_data2", chatroom.toString())


//                    chatroomAdapter.getchatroomAdapter(arrayList!!)
                chatroomAdapter.getchatroomAdapter(arrayList!!)
                    chatroombinding!!.chatroomRecycler.adapter = chatroomAdapter //setAdapter
                }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("chatroom_data-error", error.toException().toString())
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        chatroombinding= null
    }

}
