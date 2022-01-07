package com.example.tasktimerappgroup4.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.RVAdapter
import kotlinx.android.synthetic.main.activity_time.*

class TimeActivity : AppCompatActivity() {

    // Variables from activity_time
    private lateinit var tvNameBig:TextView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnRefresh: ImageButton
    private lateinit var backButton: Button
    lateinit var timer: Chronometer

    var running: Boolean = false
    var pauseOffset: Long = 0
    var total: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        // Initializing variables from activity_time
        tvNameBig = findViewById(R.id.tvNameBig)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnRefresh = findViewById(R.id.btnRefresh)
        backButton = findViewById(R.id.backToMainButton)
        timer = findViewById(R.id.chronometer)


        var name = intent.getStringExtra("title")
        tvNameBig.text = name

        btnPlayPause.setOnClickListener {
            // When click:
            // 1- Change the shape from play to pause and vice versa
            // 2- Start time

            //check if timer is running
            if(running == false){
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset)
                //start timer
                chronometer.start()
                running = true

                //change button to pause shape
            }
            //if timer is running
            else if(running == true){
                //pause
                chronometer.stop()
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
                running = false

                //change button to start shape
            }
        }

        btnRefresh.setOnClickListener {
            // Set time back to 00:00
            chronometer.setBase(SystemClock.elapsedRealtime())
            Log.d("in the TimeActivity","${pauseOffset / 1000}")
            total = total + pauseOffset
            Log.d("in the total","${total}")
            pauseOffset = 0
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

