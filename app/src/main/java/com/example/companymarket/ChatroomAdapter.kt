package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.ChatroomListBinding

class ChatroomAdapter : RecyclerView.Adapter<ChatroomAdapter.ViewHolder> (){

    private var arrayList: ArrayList<Product> = arrayListOf()

    fun getchatroomAdapter(chatroomDataset : ArrayList<Product>) {
        this.arrayList = chatroomDataset
        Log.d("array_data", arrayList.toString())

        var i : Product = arrayList.get(0)
        Log.d("array_data2", i.toString())
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
            binding.chatroomUserName.text = "Uid: ${data.pro_name}"
        }
    }

}