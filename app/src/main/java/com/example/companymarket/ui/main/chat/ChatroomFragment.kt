package com.example.companymarket.ui.main.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.Data.Chat
import com.example.companymarket.Data.Product
import com.example.companymarket.databinding.FragmentChatroomBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class ChatroomFragment : Fragment() {
    private var chatroombinding :FragmentChatroomBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var arrayList: ArrayList<Product>? = null
    private var arrayList2: ArrayList<Chat>? = null
    private val chatroomAdapter =  ChatroomAdapter()

    private val user = Firebase.auth.currentUser

    private val fireDatabase = FirebaseDatabase.getInstance().reference

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

        databaseReference = database!!.getReference("Product") // 파이어베이스 Chatroom 테이블 연결
        Log.d("Chatroom_db_data", databaseReference.toString())


        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 파이어 베이스 데이터 받아옴

                arrayList!!.clear() //배열리스트 초기화

                for (snapshot1 in snapshot.children) {
                    val product = snapshot1.getValue(
                        Product::class.java
                    )
                    arrayList!!.add(product!!)

                    Log.d("chatroom_data", arrayList!!.toString()) // 모든 arrayList
                    Log.d("chatroom_data2", product.toString()) // 최근 채팅
                    //Log.d("chatroom_data3", chatroom.chat_userName)


                    chatroomAdapter.getchatroomAdapter(arrayList!!)
                    chatroombinding!!.chatroomRecycler.adapter = chatroomAdapter //setAdapter
                }
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