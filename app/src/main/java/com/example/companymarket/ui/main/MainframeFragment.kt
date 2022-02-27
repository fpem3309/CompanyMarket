package com.example.companymarket.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.companymarket.R
import com.example.companymarket.databinding.FragmentMainframeBinding

class MainframeFragment : Fragment() {

    private var fragmentMainBinding: FragmentMainframeBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        fragmentMainBinding = FragmentMainframeBinding.inflate(inflater, container, false)
            val view = fragmentMainBinding!!.root

        replaceFragment(MainproductFragment())
        fragmentMainBinding!!.bnvMain.run {
                setOnItemSelectedListener { item->
                    when(item.itemId) {
                        R.id.first -> {
                            Toast.makeText(context, "First", Toast.LENGTH_SHORT).show()
                            replaceFragment(MainproductFragment())
                        }
                        R.id.second -> {
                            Toast.makeText(context, "second", Toast.LENGTH_SHORT).show()
                            replaceFragment(ChatroomFragment())
                        }
                        R.id.third -> {
                            Toast.makeText(context, "third", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
               }
        }
            return view
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }


}