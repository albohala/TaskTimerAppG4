package com.example.tasktimerappgroup4.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.TaskViewModel
import kotlinx.android.synthetic.main.activity_time.*
import java.util.concurrent.TimeUnit

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

    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        // Initializing variables from activity_time
        tvNameBig = findViewById(R.id.tvNameBig)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnRefresh = findViewById(R.id.btnRefresh)
        backButton = findViewById(R.id.backToMainButton)
        timer = findViewById(R.id.chronometer)

        //initializing viewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        var title = intent.getStringExtra("title")
        var id = intent.getIntExtra("id", 0)
        var taskTime = intent.getLongExtra("taskTime", 0)

        tvNameBig.text = title

        btnPlayPause.setOnClickListener {
            // When click:
            // 1- Change the shape from play to pause and vice versa
            // 2- Start time

            //check if timer is running
            if (running == false) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset)
                //start timer
                chronometer.start()
                running = true
                taskViewModel.updateTaskStatus(true,id)
                //change button to pause shape
            }
            //if timer is running
            else if (running == true) {
                //pause
                chronometer.stop()
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
                running = false
                taskViewModel.updateTaskStatus(false,id)

                //change button to start shape
            }
        }

        btnRefresh.setOnClickListener {
            // Set time back to 00:00
            chronometer.setBase(SystemClock.elapsedRealtime())
            total = total + pauseOffset + taskTime
            taskViewModel.updateTaskTime(total, id)

            pauseOffset = 0
        }

        backButton.setOnClickListener {
            //if back button pressed store the running time
            if (running == true){
               val toast =  Toast.makeText(baseContext, "please stop the timer before you go back",Toast.LENGTH_LONG)
                toast.show()
            }else{
                //store running timer
                chronometer.setBase(SystemClock.elapsedRealtime())
                total = total + pauseOffset + taskTime

                taskViewModel.updateTaskTime(total, id)
                taskViewModel.updateTaskStatus(false,id)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

