package com.example.tasktimerappgroup4.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.Adapter.RVAdapter
import com.example.tasktimerappgroup4.TaskViewModel

class MainActivity : AppCompatActivity() {
    // Variables from activity_main
    private lateinit var clMain: ConstraintLayout
    private lateinit var rvMain: RecyclerView
    private lateinit var totalTime: TextView
    private lateinit var btnAdd: Button

    lateinit var rvAdapter: RVAdapter
    lateinit var taskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing variables from activity_main
        clMain = findViewById(R.id.clMain)
        rvMain = findViewById(R.id.rvMain)
        totalTime = findViewById(R.id.tvTotalTime)
        btnAdd = findViewById(R.id.btnAdd)


        rvAdapter = RVAdapter(this)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        //initializing viewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //getting and updating notes
        taskViewModel.getAllTasks().observe(this, {
                tasks -> rvAdapter.updateData(tasks)
        })

        btnAdd.setOnClickListener{
            // intent to add task activity

            val intent = Intent(this, addTaskActivity::class.java)
            startActivity(intent)

        }
    }




//    private fun dialogBuild(){
//        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
//        dialogBuilder.setView(R.layout.dialog_builder_add)
//        title = findViewById(R.id.etTitle)
//        description = findViewById(R.id.etDetails)
//
//        dialogBuilder
//            // if yes button action is clicked
//            .setPositiveButton("add", DialogInterface.OnClickListener { _, id ->
//               //TODO:add task adding functionality
//
//                Toast.makeText(
//                    this,
//                    "Task successfully added to database",
//                    Toast.LENGTH_SHORT
//                ).show()
//            })
//            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
//                dialog.cancel()
//            })
//        val alert = dialogBuilder.create()
//        // show alert dialog
//        alert.show()
//    }
}