package com.example.companymarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.databinding.PopularListBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ViewHolder> () {

    private val dataSet: ArrayList<PopularProduct> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PopularListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    fun addData(name: String, price: Int, status: Status, content: List<Int>){
        dataSet.add(PopularProduct(name, price, status, content))
        notifyItemInserted(dataSet.size)
        Log.d("data", dataSet.toString());
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(private val binding: PopularListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:PopularProduct){
            binding.productName.text = "Name: ${data.product_name}"
            binding.productPrice.text = "Price: ${data.product_price}"
            binding.productStatus.text = "Status: ${data.product_status}"
            binding.productContent.text = "Content: ${data.product_content}"

        }
    }
}
