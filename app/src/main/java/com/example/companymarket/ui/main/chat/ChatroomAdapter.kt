package com.example.companymarket.ui.main.chat

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.companymarket.Data.Chat
import com.example.companymarket.Data.Product
import com.example.companymarket.databinding.ChatroomListBinding
import com.google.firebase.database.*

class ChatroomAdapter : RecyclerView.Adapter<ChatroomAdapter.ViewHolder> (){

    private var arrayList: ArrayList<Product> = arrayListOf()

    fun getchatroomAdapter(chatroomDataset : ArrayList<Product>) {
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

        fun bind(data: Product) {

            Glide.with(itemView).load("${data.pro_Image}").into(binding.chatroomUserImg)
            binding.chatroomUserName.text = "Uid: ${data.pro_uid}"
            binding.chatroomRecentMessage.text = "UserName: ${data.pro_name}"

            var database = FirebaseDatabase.getInstance().getReference("Chatroom").child("${data.pro_uid}").child(
                "${data.pro_name}")
            Log.d("database_data", database.toString())

                database.addValueEventListener(object : ValueEventListener {
                    var arrayList2: ArrayList<Chat>? = arrayListOf()
                    override fun onDataChange(snapshot: DataSnapshot) {

                        Log.d(TAG, "onDataChange_data:" + snapshot.key)
                        Log.d(TAG, "onDataChange_data2:" + snapshot.value)
                        Log.d(TAG, "onDataChange_data3:" + snapshot.children)

                        for (snapshot1 in snapshot.children) {
                            val chat = snapshot1.getValue(Chat::class.java)
                            Log.d(TAG, "onDataChange_data4:" + snapshot1.value)

                            arrayList2!!.add(chat!!)
                            Log.d("array2_data", arrayList2.toString())
                            Log.d("chat2_data", chat.toString())
                            binding.chatroomRecentTime.text = "RecentMessage: ${chat.chat_userMessage}"
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

            itemView.setOnClickListener{
                Intent(itemView.context, ChatActivity::class.java).apply {
                    putExtra("Chat_Uid","${data.pro_uid}")
                    putExtra("Chat_ProductName","${data.pro_name}")
                }.run { itemView.context.startActivity(this) }
            }
        }


    }

}