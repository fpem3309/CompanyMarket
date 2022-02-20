package com.example.companymarket.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.companymarket.*
import com.example.companymarket.databinding.FragmentHomeBinding
import com.google.firebase.database.*
import java.util.ArrayList


class HomeFragment : Fragment() {

  private var _binding : FragmentHomeBinding? = null
  private val binding get() = _binding!!

  private var database: FirebaseDatabase? = null
  private var databaseReference: DatabaseReference? = null
  private var arrayList: ArrayList<Product>? = null

  // RecyclerView.adapter에 지정할 Adapter
  //private lateinit var popularAdapter: PopularAdapter
  //private lateinit var recyclerview : RecyclerView

  private val popularAdapter =  PopularAdapter()

//  private val dataSet: ArrayList<PopularProduct> = arrayListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    // Inflate the layout for this fragment
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    binding.recyclerview.layoutManager = GridLayoutManager(context,2)

    return view
  }

  override fun onResume() {
    super.onResume()
    //popularAdapter.addData("맥북 에어", 1160000, Status.Sale, listOf(1160000, 100000))
    //popularAdapter.addData("맥북 프로", 1560000, Status.Soldout, listOf(1560000, 130000))
    //binding.recyclerview.adapter = popularAdapter
    //binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

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

          popularAdapter.getPopularAdapter(arrayList!!)
          binding.recyclerview.adapter = popularAdapter //setAdapter
        }
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("user_error", error.toException().toString())
      }
    })


//    popularAdapter.getPopularAdapter(arrayList!!)
//    binding.recyclerview.adapter = popularAdapter //setAdapter
  }




  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}