package com.example.companymarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.PopularAdapter
import com.example.companymarket.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

  private var _binding : FragmentHomeBinding? = null
  private val binding get() = _binding!!

  // RecyclerView.adapter에 지정할 Adapter
  //private lateinit var popularAdapter: PopularAdapter
  //private lateinit var recyclerview : RecyclerView

  private val dataSet: ArrayList<List<String>> = arrayListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    addData()
    binding.recyclerview.layoutManager = GridLayoutManager(context,2)
    binding.recyclerview.adapter = PopularAdapter(dataSet)
    binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    return view
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun addData() {
    for (i in 1..10) {
      dataSet.add(listOf("$i th main", "$i th sub"))
    }
  }
}