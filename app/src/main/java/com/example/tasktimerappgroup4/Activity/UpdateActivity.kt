package com.example.tasktimerappgroup4.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.TaskViewModel
import com.example.tasktimerappgroup4.databinding.ActivityUpdateBinding

class updateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val title = intent.getStringExtra("title")
        val description= intent.getStringExtra("description")
        val id = intent.getIntExtra("id",0)
        val taskTime = intent.getLongExtra("taskTime",0)
        val isRunning = intent.getBooleanExtra("isRunning",false)


        binding.etTitle.hint = title
        binding.etDetails.hint = description

       binding.updateButton.setOnClickListener{

           if (binding.etTitle.text != null && binding.etDetails.text != null) {
               val newTitle = binding.etTitle.text.toString()
               val newDescription = binding.etDetails.text.toString()


               taskViewModel.updateTask(Tasks(id,newTitle, newDescription,taskTime,isRunning))
           }

           val intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
       }
    }
}