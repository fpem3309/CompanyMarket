package com.example.companymarket

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.content.Intent
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.companymarket.databinding.ActivityContentBinding
import com.example.companymarket.ui.main.chat.ChatActivity


class ContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readContent()

        // SecurityException:Permission Denial: reading com.android.providers.media.MediaProvider uri content: 에러 발생시
        // API 23이상부터 권한을 기기에서 직접 허락
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Explain to the user why we need to read the contacts
            }
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
            return
        }

    }

    private fun readContent(){
        val intent = intent
        val bundle = intent.extras
        val getProductImage = bundle!!.getString("product_Image")
        val getProductUid = bundle.getString("product_Uid")
        val getProductName = bundle.getString("product_name")
        val getProductPrice = bundle.getString("product_price")
        val getProductStatus = bundle.getString("product_status")
        val getProductContent = bundle.getString("product_content")

        Log.d("image_data", getProductImage!!)

        Glide.with(this).load(getProductImage).into(binding.getProductImage)
        binding.getProductName.setText(getProductName)
        binding.getProductPrice.setText(getProductPrice)
        binding.getProductStatus.setText(getProductStatus)
        binding.getProductContent.setText(getProductContent)

        binding.btnChat.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this@ContentActivity, ChatActivity::class.java)
            intent1.putExtra("Chat_Uid", getProductUid)
            intent1.putExtra("Chat_ProductName", getProductName)
            startActivity(intent1)
        })
    }


    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 10
    }
}