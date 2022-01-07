package com.example.tasktimerappgroup4.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimerappgroup4.TaskViewModel
import com.example.tasktimerappgroup4.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    lateinit var taskViewModel: TaskViewModel
    lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
//
//
//        val title = binding.etTitle.text
//        val details = binding.etDetails.text
//        val add = binding.addButton
//
//        add.setOnClickListener {
//            Log.d("Add Activity" , "Add button has been clicked")
//
//            if(title.isNotEmpty()||details.isNotEmpty()){
//                taskViewModel.insertTask(binding.etTitle.text.toString(),binding.etDetails.text.toString(),0,false)
//                Log.d("Add task activity1", "$title has been added")
//                binding.etTitle.text = null
//                binding.etDetails.text = null
//            }else{
//                // please fill in all the fields -alert
//            }
//        }


    }
}