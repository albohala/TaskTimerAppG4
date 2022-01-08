package com.example.tasktimerappgroup4.Adapter

import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.MainActivity
//import com.example.tasktimerappgroup4.Activity.TimeActivity
import com.example.tasktimerappgroup4.Activity.updateActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.TaskViewModel
import com.example.tasktimerappgroup4.databinding.ItemRowBinding
import kotlinx.android.synthetic.main.activity_time.*
import kotlinx.android.synthetic.main.item_row.view.*
import java.util.concurrent.TimeUnit

class RVAdapter(private val activity: MainActivity): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var tasksList = emptyList<Tasks>()
    var myTasks = arrayListOf<Tasks>()

    var total: Long = 0


    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
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

                if(task.isRunning == false && task.isClicked == false){

                    chronometer.base = SystemClock.elapsedRealtime() - task.pauseOffset
                    chronometer.start()

                    task.isClicked = true
                    task.isRunning = true
                    activity.taskViewModel.updateTask(task)

                }
                else if (task.isClicked == true && task.isRunning == true){
                    Log.d("second else", "Please stop the running timer ")
                }


                if(task.isRunning == false){
                    for (activeTask in tasksList){
                        if(activeTask.isRunning){
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
                //intent to update activite
                val intent = Intent(holder.itemView.context, updateActivity::class.java)
                intent.putExtra("id", task.id)
                intent.putExtra("title", task.title)
                intent.putExtra("description", task.description)
                intent.putExtra("taskTime", task.taskTime)
                intent.putExtra("isRunning", task.isRunning)
                intent.putExtra("totalTime", task.totalTime)
                intent.putExtra("isClicked", task.isClicked)
                intent.putExtra("pauseOffset", task.pauseOffset)
                holder.itemView.context.startActivity(intent)
            }


            //the delete button pressed
            btnDelete.setOnClickListener {
                activity.taskViewModel.deleteTask(task.id)
            }


            //setting the total time for rach task
//            val timeConverted = timeCoverter(task.taskTime)
//            tvTimeSmall.text = "$timeConverted"


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

    private fun restartTimer(task:Tasks, chronometer: Chronometer){
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