package com.example.companymarket.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.companymarket.databinding.FragmentChatroomBinding

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