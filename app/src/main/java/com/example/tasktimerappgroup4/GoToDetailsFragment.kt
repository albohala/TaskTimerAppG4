package com.example.tasktimerappgroup4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_go_to_details.view.*

class GoToDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_go_to_details, container, false)
        view.findViewById<MaterialButton>(R.id.fabNext).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_goToDetailsFragment_to_secondInstructionsFragment2)
        }
        return view
    }

}