package com.example.tasktimerappgroup4.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Adapter.RVAdapter
import com.example.tasktimerappgroup4.Adapter.TotalAdapter
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.TaskViewModel

class TotalActivity : AppCompatActivity() {

    private lateinit var rvTotal: RecyclerView

    lateinit var rvTotalAdapter: TotalAdapter
    lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)


        rvTotal = findViewById(R.id.rvTotal)


        rvTotalAdapter = TotalAdapter(this)
        rvTotal.adapter = rvTotalAdapter
        rvTotal.layoutManager = LinearLayoutManager(this)

        //initializing viewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //getting and updating notes
        taskViewModel.getAllTasks().observe(this, {
                tasks -> rvTotalAdapter.updateData(tasks)
        })
    }
}