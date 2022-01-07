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


        val title = binding.etTitle.text
        val details = binding.etDetails.text
        val add = binding.addButton

        add.setOnClickListener {
            if(title.isNotEmpty()||details.isNotEmpty()){
                taskViewModel.insertTask(binding.etTitle.text.toString(),binding.etDetails.text.toString(),0,false)
                binding.etTitle.text = null
                binding.etDetails.text = null

                //alert task added successfully
            }else{
                // please fill in all the fields -alert
            }
        }
    }
}