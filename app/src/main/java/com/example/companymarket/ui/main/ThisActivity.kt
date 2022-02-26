package com.example.companymarket.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.companymarket.R
import com.example.companymarket.databinding.ActivityThisBinding
import com.example.companymarket.databinding.FragmentMainproductBinding

class ThisActivity : Fragment() {

    private var thisBinding: ActivityThisBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            thisBinding = ActivityThisBinding.inflate(inflater, container, false)
            val view = thisBinding!!.root

        replaceFragment(MainproductFragment())
        thisBinding!!.bnvMain.run {
                setOnItemSelectedListener { item->
                    when(item.itemId) {
                        R.id.first -> {
                            Toast.makeText(context, "First", Toast.LENGTH_SHORT).show()
                            replaceFragment(MainproductFragment())
                        }
                        R.id.second -> {
                            Toast.makeText(context, "second", Toast.LENGTH_SHORT).show()
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