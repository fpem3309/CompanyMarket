package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.ChatroomListBinding

class ChatroomAdapter : RecyclerView.Adapter<ChatroomAdapter.ViewHolder> (){

    private var arrayList: ArrayList<Chat> = arrayListOf()

    fun getchatroomAdapter(chatroomDataset : ArrayList<Chat>) {
        this.arrayList = chatroomDataset
        Log.d("array_data", arrayList.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatroomListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ViewHolder(private val binding: ChatroomListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Chat) {
            //Glide.with(itemView).load("${data.pro_Image}").into(binding.productImage)
            binding.chatroomUserName.text = "Uid: ${data.chat_userName}"
            binding.chatroomRecentMessage.text = "${data.chat_userMessage}"
            binding.chatroomRecentTime.text = "${data.chat_userTime}"
        }
    }

}