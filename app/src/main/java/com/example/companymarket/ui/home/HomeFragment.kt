package com.example.companymarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.companymarket.PopularAdapter
import com.example.companymarket.TestData
import com.example.companymarket.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

  private var _binding : FragmentHomeBinding? = null
  private val binding get() = _binding!!

  // RecyclerView.adapter에 지정할 Adapter
  private lateinit var popularAdapter: PopularAdapter
  private lateinit var recyclerview : RecyclerView

  private val dataSet: ArrayList<List<String>> = arrayListOf()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    addData()
    binding.recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    binding.recyclerview.adapter = PopularAdapter(dataSet)
    binding.recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    return view
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
  private fun addData() {
    for (i in 0..99) {
      dataSet.add(listOf("$i th main", "$i th sub"))
    }
// return inflater.inflate(R.layout.fragment_home, container, false)

//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//
//    var list: ArrayList<TestData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<TestData>
//    Log.e("FirstFragment", "Data List: ${list}")
//
//    // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
//    popularAdapter = PopularAdapter(list)
//    recyclerview.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//    // RecyclerView.adapter에 지정
//    recyclerview.adapter = popularAdapter
//  }
  }
}