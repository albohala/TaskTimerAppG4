package com.example.tasktimerappgroup4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class TimeActivity : AppCompatActivity() {
    // Variables from item_row
    private lateinit var tvTimeSmall: TextView
    private lateinit var tvNameSmall: TextView
    private lateinit var tvDescription: TextView

    // Variables from activity_time
    private lateinit var tvTimeBig: TextView
    private lateinit var tvNameBig:TextView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnRefresh: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        // Initializing variables from item_row
//        tvTimeSmall = findViewById(R.id.tvTimeSmall)
//        tvNameSmall = findViewById(R.id.tvNameSmall)
//        tvDescription = findViewById(R.id.tvDescription)

        // Initializing variables from activity_time
        tvTimeBig = findViewById(R.id.tvTimeBig)
        tvNameBig = findViewById(R.id.tvNameBig)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnRefresh = findViewById(R.id.btnRefresh)

        btnPlayPause.setOnClickListener {
            // When click:
            // 1- Change the shape from play to pause and vice versa
            // 2- Start time
        }

        btnRefresh.setOnClickListener {
            // Set time back to 00:00
        }
    }
}