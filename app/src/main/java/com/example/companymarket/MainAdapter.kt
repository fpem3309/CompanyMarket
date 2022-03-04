package com.example.companymarket

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.companymarket.Data.Product
import com.example.companymarket.databinding.MainListBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder> () {

    private var arrayList: ArrayList<Product> = arrayListOf()

    fun getMainAdapter(productDataset : ArrayList<Product>) {
        this.arrayList = productDataset
        Log.d("array_data", arrayList.toString())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = MainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        //holder.bind(dataSet[position])
        holder.bind(arrayList[position])
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(private val binding: MainListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Product){
            //binding.productImage.setImageResource("${data.pro_Image}")
            Glide.with(itemView).load("${data.pro_Image}").into(binding.productImage)
            binding.productUid.text = "Uid: ${data.pro_uid}"
            binding.productName.text = "${data.pro_name}"
            binding.productPrice.text = "${data.pro_price}원"
            binding.productStatus.text = "${data.pro_status}"
            binding.productContent.text = "${data.pro_content}"


            itemView.setOnClickListener {
                Intent(itemView.context, ContentActivity::class.java).apply {
                    putExtra("product_Image",data.pro_Image)
                    putExtra("product_Uid",data.pro_uid)
                    putExtra("product_name",data.pro_name)
                    putExtra("product_price",data.pro_price.toString())
                    putExtra("product_status",data.pro_status.toString())
                    putExtra("product_content",data.pro_content.toString())

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { itemView.context.startActivity(this) }
            }
        }
    }

}
