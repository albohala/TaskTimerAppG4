package com.example.tasktimerappgroup4.Activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
            //pop add task dialog box
            dialogBuild()
//            val intent = Intent(this, AddTaskActivity::class.java)
//            startActivity(intent)
        }
    }



    private fun dialogBuild() {
        val dialogBuilder = Dialog(this)
        dialogBuilder.setContentView(R.layout.dialog_builder_add)
        dialogBuilder.window?.setBackgroundDrawableResource(R.drawable.dialog_window)

//        var title = dialogBuilder.etTitle.text
//        var details = dialogBuilder.etDetails.text
//        val add = dialogBuilder.btSubmit

        //button interaction
//        add.setOnClickListener {
//            //add to database functionality
//            if(title.isNotEmpty()||details.isNotEmpty()){
//                taskViewModel.insertTask(dialogBuilder.etTitle.text.toString(),dialogBuilder.etDetails.text.toString(),0,false)
//                Log.d("Add task activity1", "$title has been added")
//                title = null
//                details = null
//                Toast.makeText(
//                    this,
//                    "Task successfully added to database",
//                    Toast.LENGTH_SHORT
//                ).show()
//                dialogBuilder.dismiss()
//            }else{
//                // please fill in all the fields -alert
//                Toast.makeText(
//                    this,
//                    "Please fill in all the required fields",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//        }
//
//        dialogBuilder.show()
    }


}