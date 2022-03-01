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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class ChatroomFragment : Fragment() {
    private var chatroombinding :FragmentChatroomBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var databaseReference2: DatabaseReference? = null
    private var arrayList: ArrayList<Chat>? = null
    private var arrayList2: ArrayList<Chat>? = null
    private val chatroomAdapter =  ChatroomAdapter()

    private val user = Firebase.auth.currentUser


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
        arrayList2 = ArrayList()
        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동

        databaseReference = database!!.getReference("Chatroom").child("a5zLnHLwDWQ2zyDGXWAe3fZJ0xm2").child("test") // 파이어베이스 Chatroom 테이블 연결
        Log.d("Chatroom_db_data", databaseReference.toString())


        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // 파이어 베이스 데이터 받아옴
                // arrayList!!.clear() //배열리스트 초기화
                    val chatroom = snapshot.getValue(Chat::class.java)

                    arrayList!!.add(chatroom!!)

                    Log.d("chatroom_data", arrayList!!.toString()) // 모든 arrayList
                    Log.d("chatroom_data2", chatroom.toString()) // 최근 채팅
                    Log.d("chatroom_data3", chatroom.chat_userName)

                var uid = user?.uid // 내 uid를 가져와서
                val recentChat : String = chatroom.chat_userMessage // 가장 최근 채팅메세지

                Log.d("arraySize_data", arrayList!!.size.toString())
                for(i in 0 .. arrayList!!.size-1 step (1)){
                    var chkUser = arrayList!!.get(i).chat_userName  // userName을 가져와서
                    Log.d("chk_data", chkUser.toString())
                    if(chkUser.equals(uid)){    //내 uid가 있으면 내 채팅목록에 추가
                        Log.d("chkUser_data","있음")
                        Log.d("chkUser_data2", arrayList!!.get(i).toString())
                        arrayList2!!.add(arrayList!!.get(i))
                    }
                }

                chatroomAdapter.getchatroomAdapter(arrayList2!!)
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
