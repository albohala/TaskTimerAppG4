package com.example.tasktimerappgroup4.Adapter

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.MainActivity
import com.example.tasktimerappgroup4.Activity.TimeActivity
import com.example.tasktimerappgroup4.Activity.updateActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.R
import com.example.tasktimerappgroup4.TaskViewModel
import com.example.tasktimerappgroup4.databinding.ItemRowBinding
import java.util.concurrent.TimeUnit

class RVAdapter(private val activity: MainActivity): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var tasksList = emptyList<Tasks>()

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
            var isplay = false
            var pauseOff: Long = 0
            var isClick = false

            //the cell is pressed
            cellRow.setOnClickListener {
                if (!isClick) {

                    chronometer.start()
                    chronometer.base = SystemClock.elapsedRealtime() - pauseOff

                    isClick = true

                }
            }

            btnRestore.setOnClickListener {
                if(!isplay){
                    chronometer.base = SystemClock.elapsedRealtime() - pauseOff
                    chronometer.start()
                    btnStop.visibility = View.VISIBLE
                    isplay = true
                    isClick = true

                }
                else {
                    chronometer.base = SystemClock.elapsedRealtime()
                    pauseOff = 0
                    chronometer.stop()
                    isplay = false
                    isClick = false
                }
            }

            btnStop.setOnClickListener {

                    chronometer.stop()
                    pauseOff = SystemClock.elapsedRealtime() - chronometer.base
                    isplay = false
                isClick = false


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


    fun timeCoverter(total: Long): String {
        val newTotal2 = String.format(
            "%02d:%02d:%02d",
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