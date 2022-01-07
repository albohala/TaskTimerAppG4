package com.example.tasktimerappgroup4

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerappgroup4.Activity.MainActivity
import com.example.tasktimerappgroup4.Activity.TimeActivity
import com.example.tasktimerappgroup4.Activity.updateActivity
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.databinding.ItemRowBinding

class RVAdapter(private val activity: MainActivity): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    var tasksList = emptyList<Tasks>()
    class ItemViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
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
                val intent = Intent(holder.itemView.context, TimeActivity::class.java)
                intent.putExtra("title", task.title)
                holder.itemView.context.startActivity(intent)
            }

            //the edit button pressed
            btnEdit.setOnClickListener{
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

            //setting the total time

            val myIntent = Intent(holder.itemView.context, TimeActivity::class.java)
            val total = myIntent.getLongExtra("total",0)

            println("here is the total from  getLongExtra $total")
            tvTimeSmall.text = "$total"

        }

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    fun updateData(notes: List<Tasks>){
        this.tasksList = notes
        notifyDataSetChanged()
    }

}