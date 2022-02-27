package com.example.companymarket

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.ChatItemBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private val user = Firebase.auth.currentUser
    private var arrayList: ArrayList<Chat> = arrayListOf()

    fun getChatAdapter(chatDataset: ArrayList<Chat>) {
        this.arrayList = chatDataset
        Log.d("array_data", arrayList.toString())
    }

//    fun addData(){
//        arrayList.add(Chat("test","test2","test"))
//        arrayList.add(Chat("test","test2","test"))
//        arrayList.add(Chat("test","test2","test"))
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChatViewHolder(binding)
    }

    inner class ChatViewHolder(private val chatBinding: ChatItemBinding): RecyclerView.ViewHolder(chatBinding.root){
        val layout_chat : LinearLayout = chatBinding.chatLayout
        fun bind(chat: Chat) {
            chatBinding.chatUserName.text = "${chat.chat_userName}"
            chatBinding.chatUserMessage.text = "${chat.chat_userMessage}"
            chatBinding.chatUserTime.text = "${chat.chat_userTime}"
        }
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        var uid = user?.uid
        holder.bind(arrayList[position])


        if(arrayList[position].chat_userName.equals(uid)){ // 내 uid랑 같으면
            holder.layout_chat.gravity = Gravity.RIGHT
        }else{
            holder.layout_chat.gravity = Gravity.LEFT
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}