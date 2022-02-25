package com.example.companymarket.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.MainAdapter
import com.example.companymarket.Product
import com.example.companymarket.databinding.FragmentMainproductBinding
import com.google.firebase.database.*
import java.util.ArrayList

class MainproductFragment : Fragment() {
    private var _binding : FragmentMainproductBinding? = null
    private val binding get() = _binding!!

    private var database: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var arrayList: ArrayList<Product>? = null

    private val mainAdapter =  MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentMainproductBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(context)

        return view
    }


    override fun onResume() {
        super.onResume()

        arrayList = ArrayList()
        database = FirebaseDatabase.getInstance() // 파이어 데이터베이스 연동
        databaseReference = database!!.getReference("Product") // 파이어베이스 Product 테이블 연결

        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 파이어 베이스 데이터 받아옴
                arrayList!!.clear() //배열리스트 초기화
                for (snapshot1 in snapshot.children) {
                    val product = snapshot1.getValue(
                        Product::class.java
                    )
                    arrayList!!.add(product!!)
                    Log.d("product_data", arrayList.toString())

                    mainAdapter.getMainAdapter(arrayList!!)
                    binding.mainRecyclerview.adapter = mainAdapter //setAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("user_error", error.toException().toString())
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}