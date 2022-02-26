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
import com.example.companymarket.databinding.FragmentChatroomBinding
import com.example.companymarket.databinding.FragmentMainproductBinding

class ChatroomFragment : Fragment() {

    private var thisBinding: FragmentChatroomBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            thisBinding = FragmentChatroomBinding.inflate(inflater, container, false)
            val view = thisBinding!!.root

        return view
    }


}