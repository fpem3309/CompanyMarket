package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.ChatItemBinding

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private var arrayList: ArrayList<Chat> = arrayListOf()

    fun getChatAdapter(chatDataset: ArrayList<Chat>) {
        this.arrayList = chatDataset
        Log.d("array_data", arrayList.toString())
    }
    fun addData(){
        arrayList.add(Chat("test","test2","test3"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChatViewHolder(binding)
    }

    inner class ChatViewHolder(private val chatBinding: ChatItemBinding): RecyclerView.ViewHolder(chatBinding.root){
        fun bind(chat: Chat) {
            chatBinding.chatUserName.text = "name : ${chat.chat_userMame}"
            chatBinding.chatUserMessage.text = "message : ${chat.chat_userMessage}"
            chatBinding.chatUserTime.text = "time : ${chat.chat_userTime}"
        }
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}