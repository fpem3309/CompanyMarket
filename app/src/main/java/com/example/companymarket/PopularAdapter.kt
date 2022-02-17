package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.PopularListBinding

class PopularAdapter(private val dataSet: ArrayList<List<String>>): RecyclerView.Adapter<PopularAdapter.ViewHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PopularListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(private val binding: PopularListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: List<String>) {
            binding.tvMain.text = data[0]
            binding.tvSub.text = data[1]
        }


    }

}
