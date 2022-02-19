package com.example.companymarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.companymarket.*
import com.example.companymarket.databinding.FragmentHomeBinding
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {

  private var _binding : FragmentHomeBinding? = null
  private val binding get() = _binding!!

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
    popularAdapter.addData("맥북 에어", 1160000, Status.Sale, listOf(1160000, 100000))
    popularAdapter.addData("맥북 프로", 1560000, Status.Soldout, listOf(1560000, 130000))
    binding.recyclerview.adapter = popularAdapter
    binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }






}