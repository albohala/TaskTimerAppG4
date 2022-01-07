package com.example.tasktimerappgroup4.Adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.MainActivity
import com.example.tasktimerappgroup4.Activity.TimeActivity
import com.example.tasktimerappgroup4.Activity.updateActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.databinding.ItemRowBinding
import kotlinx.android.synthetic.main.dialog_builder_add.*
import kotlinx.android.synthetic.main.dialog_builder_add.etTitle
import kotlinx.android.synthetic.main.dialog_builder_edit.*

import java.util.concurrent.TimeUnit

class RVAdapter(private val activity: MainActivity) :
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    private var tasksList = emptyList<Tasks>()
    private lateinit var activityContext: Context

    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        activityContext = parent.context
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

            //the cell is pressed
            cellRow.setOnClickListener {
//                val intent = Intent(holder.itemView.context, TimeActivity::class.java)
//                intent.putExtra("title", task.title)
//                holder.itemView.context.startActivity(intent)
//                val intent = Intent(holder.itemView.context, TimeActivity::class.java)
//                intent.putExtra("taskTime",task.taskTime)
//                intent.putExtra("title", task.title)
//                intent.putExtra("id", task.id)
//
//                holder.itemView.context.startActivity(intent)
            }


            //the edit button pressed
            btnEdit.setOnClickListener {
                //pop alert to update activity
                val taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

                val dialogBuilder = Dialog(activityContext)
                dialogBuilder.setContentView(R.layout.dialog_builder_edit)
                dialogBuilder.window?.setBackgroundDrawableResource(R.drawable.dialog_window)

                var titleE = dialogBuilder.etTitleE.text
                var detailsE = dialogBuilder.etDetailsE.text
                val edit = dialogBuilder.btSubmitE
                dialogBuilder.etTitleE.hint = title
                dialogBuilder.etDetailsE.hint = description
                //button interaction
                edit.setOnClickListener {
                    //TODO:update to database functionality
                    if (titleE != null && detailsE != null) {
                        val newTitle = titleE.toString()
                        val newDescription = detailsE.toString()

                        taskViewModel.updateTask(
                            Tasks(
                                task.id,
                                newTitle,
                                newDescription,
                                task.taskTime,
                                task.isRunning
                            )
                        )
                        dialogBuilder.dismiss()
                    }

                }

                dialogBuilder.show()
                //                val intent = Intent(holder.itemView.context, updateActivity::class.java)
//                intent.putExtra("id", task.id)
//                intent.putExtra("title", task.title)
//                intent.putExtra("description", task.description)
//                intent.putExtra("taskTime", task.taskTime)
//                intent.putExtra("isRunning", task.isRunning)
//                holder.itemView.context.startActivity(intent)

            }


            //the delete button pressed
            btnDelete.setOnClickListener {
                activity.taskViewModel.deleteTask(task.id)
            }



            //setting the total time for rach task
            val timeConverted = timeCoverter(task.taskTime)
            tvTimeSmall.text = "$timeConverted"


            //setting the total time for all tasks

        }
    }



    fun timeCoverter(total: Long): String{
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

    private fun dialogBuild2() {

    }
}