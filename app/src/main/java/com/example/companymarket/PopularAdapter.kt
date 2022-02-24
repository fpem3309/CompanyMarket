package com.example.companymarket

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.companymarket.databinding.PopularListBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ViewHolder> () {

    private var arrayList: ArrayList<Product> = arrayListOf()
    private val dataSet: ArrayList<PopularProduct> = arrayListOf()

    fun getPopularAdapter(productDataset : ArrayList<Product>) {
        this.arrayList = productDataset
        Log.d("array_data", arrayList.toString())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PopularListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    fun addData( name: String, price: Int, status: Status, content: List<Int>){
        dataSet.add(PopularProduct( name, price, status, content))
        notifyItemInserted(dataSet.size)
        Log.d("Frg -> adapter data", dataSet.toString());
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        //holder.bind(dataSet[position])
        holder.bind(arrayList[position])
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(private val binding: PopularListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Product){
            //binding.productImage.setImageResource("${data.pro_Image}")
            Glide.with(itemView).load("${data.pro_Image}").into(binding.productImage)
            binding.productName.text = "Name: ${data.pro_name}"
            binding.productPrice.text = "Price: ${data.pro_price}"
            binding.productStatus.text = "Status: ${data.pro_status}"
            binding.productContent.text = "Content: ${data.pro_content}"

            itemView.setOnClickListener {
                Intent(itemView.context, ContentActivity::class.java).apply {
                    putExtra("product_Image",data.pro_Image)
                    putExtra("product_name",data.pro_name)
                    putExtra("product_price",data.pro_price.toString())
                    putExtra("product_status",data.pro_status.toString())
                    putExtra("product_content",data.pro_content.toString())

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { itemView.context.startActivity(this) }
            }
        }

//        fun bind(data:PopularProduct){
//            binding.productName.text = "Name: ${data.product_name}"
//            binding.productPrice.text = "Price: ${data.product_price}"
//            binding.productStatus.text = "Status: ${data.product_status}"
//            binding.productContent.text = "Content: ${data.product_content}"
//
//            itemView.setOnClickListener {
//                Intent(itemView.context, ContentActivity::class.java).apply {
//                    putExtra("product_name",data.product_name)
//                    putExtra("product_price",data.product_price.toString())
//                    putExtra("product_status",data.product_status.toString())
//
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { itemView.context.startActivity(this) }
//            }
//        }
    }

}
