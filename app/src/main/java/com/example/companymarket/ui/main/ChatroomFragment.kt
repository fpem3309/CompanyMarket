package com.example.companymarket.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.companymarket.databinding.FragmentChatroomBinding
import com.google.firebase.database.*

class ChatroomFragment : Fragment() {
    private var thisBinding :FragmentChatroomBinding? = null

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        thisBinding = FragmentChatroomBinding.inflate(inflater, container, false)
        val view = thisBinding!!.root

        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        databaseReference = database!!.getReference("Chatroom") // 파이어베이스 Chatroom 테이블 연결
        Log.d("db", databaseReference.toString())

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        thisBinding= null
    }

}
