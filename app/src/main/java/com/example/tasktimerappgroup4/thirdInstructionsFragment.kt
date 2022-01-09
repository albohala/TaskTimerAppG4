package com.example.tasktimerappgroup4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.tasktimerappgroup4.Activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_go_to_details.view.*
import kotlinx.android.synthetic.main.fragment_go_to_details.view.fabNext
import kotlinx.android.synthetic.main.fragment_third_instructions.view.*


class thirdInstructionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_third_instructions, container, false)
        view.fabNext3.setOnClickListener {
Navigation.findNavController(view).navigate(R.id.action_thirdInstructionsFragment_to_mainActivity)        }
        return view    }

}