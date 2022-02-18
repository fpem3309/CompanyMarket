package com.example.companymarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.companymarket.PopularAdapter
import com.example.companymarket.PopularProduct
import com.example.companymarket.Status
import com.example.companymarket.databinding.FragmentHomeBinding
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
    binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    return view
  }

  override fun onResume() {
    super.onResume()
    popularAdapter.addData("멕북 에어", 1160000, Status.Sale, listOf(1160000,100000))
    popularAdapter.addData("멕북 프로", 1560000, Status.Sale, listOf(1560000,130000))
    binding.recyclerview.adapter = popularAdapter
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}