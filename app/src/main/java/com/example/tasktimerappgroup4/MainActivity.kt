package com.example.tasktimerappgroup4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // Variables from activity_main
    private lateinit var clMain: ConstraintLayout
    private lateinit var rvMain: RecyclerView
    private lateinit var totalTime: TextView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing variables from activity_main
        clMain = findViewById(R.id.clMain)
        rvMain = findViewById(R.id.rvMain)
        totalTime = findViewById(R.id.tvTotalTime)
        btnAdd = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener{
            // Add data to recycle view
        }
        // Comment
    }
}