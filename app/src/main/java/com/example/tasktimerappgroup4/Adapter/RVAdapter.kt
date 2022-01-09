package com.example.tasktimerappgroup4.Adapter

import android.app.Dialog
import android.content.Context
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.MainActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.databinding.ItemRowBinding

import kotlinx.android.synthetic.main.dialog_builder_edit.*
import java.util.concurrent.TimeUnit

class RVAdapter(private val activity: MainActivity) :
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var tasksList = emptyList<Tasks>()
    var myTasks = arrayListOf<Tasks>()

    var total: Long = 0
    lateinit var context: Context

    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = tasksList[position]


        holder.binding.apply {

            val title = "${task.title}"
            val description = "${task.description}"

            tvNameSmall.text = title
            tvDescription.text = description


            task.isRunning = false
            task.isClicked = false


            chronometer.text = timeCoverter(task.pauseOffset)


            //the cell is pressed
            cellRow.setOnClickListener {

                if (task.isRunning == false && task.isClicked == false) {

                    chronometer.base = SystemClock.elapsedRealtime() - task.pauseOffset
                    chronometer.start()

                    task.isClicked = true
                    task.isRunning = true
                    activity.taskViewModel.updateTask(task)

                } else if (task.isClicked == true && task.isRunning == true) {
                    Log.d("second else", "Please stop the running timer ")
                }


                if (task.isRunning == false) {
                    for (activeTask in tasksList) {
                        if (activeTask.isRunning) {
                            stopTimer(activeTask, chronometer)
                            println("in the stop timer check: ${activeTask.title}")
                        }
                        println("here is the task count")
                    }

                    println("chronometer started")
                    chronometer.start()
                    task.isRunning = true
                    task.isClicked = true

                    activity.taskViewModel.updateTask(task)
                }
            }

            btnRestore.setOnClickListener {
                // Set time back to 00:00
                restartTimer(task, chronometer)
            }

            btnStop.setOnClickListener {
                stopTimer(task, chronometer)

            }


            //the edit button pressed
            btnEdit.setOnClickListener {
                val dialogBuilder = Dialog(context)
                dialogBuilder.setContentView(R.layout.dialog_builder_edit)
                dialogBuilder.window?.setBackgroundDrawableResource(R.drawable.dialog_window)

                var myTitleE = dialogBuilder.etTitleE.text
                var myDetailsE = dialogBuilder.etDetailsE.text
                val edit = dialogBuilder.btSubmitE

                //button interaction
                edit.setOnClickListener {
                    //add to database functionality
                    if (myTitleE.isNotEmpty() || myDetailsE.isNotEmpty()) {
                        val task = Tasks(
                            task.id,
                            dialogBuilder.etTitleE.text.toString(),
                            dialogBuilder.etDetailsE.text.toString(),
                            "00:00",
                            "00:00:00",
                            isRunning = false,
                            isClicked = false,
                            pauseOffset = 0L
                        )
                        activity.taskViewModel.updateTask(task)
                        Log.d("Add task activity1", "$title has been added")
                        Toast.makeText(
                            context,
                            "Task successfully added to database",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialogBuilder.dismiss()
                    } else {
                        // please fill in all the fields -alert
                        Toast.makeText(
                            context,
                            "Please fill in all the required fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                dialogBuilder.show()

            }


            //the delete button pressed
            btnDelete.setOnClickListener {
                activity.taskViewModel.deleteTask(task.id)
            }

            //setting the total time for rach task

            //setting the total time for all tasks

        }
    }

    private fun stopTimer(task: Tasks, chronometer: Chronometer) {
        task.taskTime = chronometer.text.toString()
        chronometer.stop()
        task.pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.text = timeCoverter(task.pauseOffset)

        task.isRunning = false
        task.isClicked = false
        activity.taskViewModel.updateTask(task)
    }

    private fun restartTimer(task: Tasks, chronometer: Chronometer) {
        task.totalTime = getTotalFromString(task.totalTime, chronometer.text.toString())
        task.taskTime = "00:00"
        chronometer.base = SystemClock.elapsedRealtime()
        task.pauseOffset = 0
        chronometer.stop()
        task.isRunning = false
        task.isClicked = false
        activity.taskViewModel.updateTask(task)

    }

    private fun getTotalFromString(oldString: String, newString: String): String {
        var oldHours = 0
        var oldMinutes = 0
        var oldSeconds = 0
        val oldStringArray = oldString.split(":")
        if (oldStringArray.size == 2) {
            oldMinutes = oldStringArray[0].toInt()
            oldSeconds = oldStringArray[1].toInt()
        } else {
            oldHours = oldStringArray[0].toInt()
            oldMinutes = oldStringArray[1].toInt()
            oldSeconds = oldStringArray[2].toInt()
        }

        var newHours = 0
        var newMinutes = 0
        var newSeconds = 0
        val newStringArray = newString.split(":")
        if (newStringArray.size == 2) {
            newMinutes = newStringArray[0].toInt() + oldMinutes
            newSeconds = newStringArray[1].toInt() + oldSeconds
        } else {
            newHours = newStringArray[0].toInt() + oldHours
            newMinutes = newStringArray[1].toInt() + oldMinutes
            newSeconds = newStringArray[2].toInt() + oldSeconds
        }

        if (newSeconds >= 60) {
            newMinutes += newSeconds / 60
            newSeconds %= 60
        }
        if (newMinutes >= 60) {
            newHours += newMinutes / 60
            newMinutes %= 60
        }

        return (if (newHours < 10) "0$newHours" else newHours).toString() +
                ":" + (if (newMinutes < 10) "0$newMinutes" else newMinutes) +
                ":" + if (newSeconds < 10) "0$newSeconds" else newSeconds
    }


    fun timeCoverter(total: Long): String {
        val newTotal2 = String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(total),
            TimeUnit.MILLISECONDS.toMinutes(total) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total)),
            TimeUnit.MILLISECONDS.toSeconds(total) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(total))
        )
        return newTotal2
    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    fun updateData(notes: List<Tasks>) {
        this.tasksList = notes
        notifyDataSetChanged()
    }

}