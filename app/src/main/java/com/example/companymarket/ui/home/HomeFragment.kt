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
  private val ppAdapter = PopularAdapter()

  private val dataSet: ArrayList<PopularProduct> = arrayListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root

    ppAdapter.addData("멕북에어", 100000, Status.Sale, listOf(100000,90000))
    ppAdapter.addData("멕북프로", 200000, Status.Sale, listOf(200000,190000))
    ppAdapter.addData("멕북맥스", 300000, Status.Sale, listOf(300000,290000))
    ppAdapter.addData("멕", 500000, Status.Sale, listOf(500000,490000))
    ppAdapter.addData("멕미니", 400000, Status.Sale, listOf(400000,390000))

    binding.recyclerview.layoutManager = GridLayoutManager(context,2)
    binding.recyclerview.adapter = PopularAdapter(dataSet)
    binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    return view
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}