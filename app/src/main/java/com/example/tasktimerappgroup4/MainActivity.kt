package com.example.tasktimerappgroup4

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Comment

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
            dialogBuild()
        }

        // Comment
    }
    private fun dialogBuild(){
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        dialogBuilder.setView(R.layout.dialog_builder_add)

        dialogBuilder
            // if yes button action is clicked
            .setPositiveButton("add", DialogInterface.OnClickListener { _, id ->
               //TODO:add task adding functionality

                Toast.makeText(
                    this,
                    "Task successfully added to database",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        // show alert dialog
        alert.show()
    }
}