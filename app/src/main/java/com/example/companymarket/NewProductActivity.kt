package com.example.companymarket

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.example.companymarket.Data.Product
import com.example.companymarket.databinding.ActivityNewproductBinding
import java.lang.Exception

class NewProductActivity : AppCompatActivity() {

    private lateinit var newProductBinding: ActivityNewproductBinding

    var user = FirebaseAuth.getInstance().currentUser
    var uid = user!!.uid

    val RALLERY_CODE = 10
    var storage: FirebaseStorage? = null
    var file: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newProductBinding = ActivityNewproductBinding.inflate(layoutInflater)
        setContentView(newProductBinding.root)
        newProduct()

        newProductBinding.btnCancle.setOnClickListener(View.OnClickListener { finish() })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        file = data!!.data
        val storageRef = storage!!.reference //스토리지 참조
        val riverRef = storageRef.child("photh/1.png")
        val uploadTask = riverRef.putFile(file!!)
        try {
            val inputStream = contentResolver.openInputStream(data.data!!)
            val img = BitmapFactory.decodeStream(inputStream)
            inputStream!!.close()
            newProductBinding.ivPreview!!.setImageBitmap(img)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        uploadTask.addOnFailureListener {
            Toast.makeText(this@NewProductActivity, "Fail", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
                Toast.makeText(this@NewProductActivity, "Success", Toast.LENGTH_SHORT).show()
            }
    }

    private fun newProduct(){

        val database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        val databaseReference = database.reference // 파이어베이스 테이블 연결
        storage = FirebaseStorage.getInstance() //스토리지 인스턴스 만들기
        newProductBinding.btnAddImage.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, RALLERY_CODE)
        })

        newProductBinding.btnAddProduct.setOnClickListener(View.OnClickListener {
            databaseReference.child("Product").push().setValue(
                Product(
                    uid, file,
                    newProductBinding.edtProductName.text.toString(),
                    newProductBinding.edtProductPrice.text.toString().toInt(),
                    newProductBinding.edtProductStatus.text.toString(),
                    newProductBinding.edtProductContent.text.toString()
                )
            )
            Log.d("uid_data", uid)
            Log.d("file_data", file.toString())
            finish()

        })
    }
}