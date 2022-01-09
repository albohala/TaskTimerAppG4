package com.example.tasktimerappgroup4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_last_inst.view.*
import kotlinx.android.synthetic.main.fragment_second_instructions.view.*
import kotlinx.android.synthetic.main.fragment_second_instructions.view.fabNextGo2


class LastInstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_last_inst, container, false)
        view.fabNext4.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_lastInstFragment_to_mainActivity)
        }
        return view
    }
}

