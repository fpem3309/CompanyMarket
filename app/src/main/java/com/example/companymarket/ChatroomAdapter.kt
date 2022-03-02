package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.ChatroomListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
            //Glide.with(itemView).load("${data.pro_Image}").into(binding.productImage)
            binding.chatroomUserName.text = "Uid: ${data.pro_uid}"
            binding.chatroomRecentMessage.text = "Uid: ${data.pro_name}"
            var database = FirebaseDatabase.getInstance().getReference("Chatroom").child(binding.chatroomUserName.text as String).child(
                binding.chatroomRecentMessage.text as String)
            Log.d("database_data", database.toString())

            database.addListenerForSingleValueEvent(object : ValueEventListener{
                var arrayList2: ArrayList<Chat>? = arrayListOf()
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (data in snapshot.children) {
                        val chat = data.getValue(
                            Chat::class.java
                        )
                        arrayList2!!.add(chat!!)
                        Log.d("dddata", arrayList2!!.get(0).chat_userName)
                        binding.chatroomRecentTime.text = arrayList2.toString()
                }
            }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

    }

}