package com.example.tasktimerappgroup4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tasktimerappgroup4.Database.TaskDatabase
import com.example.tasktimerappgroup4.Model.Tasks
import com.example.tasktimerappgroup4.Repository.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TasksRepository
    private val tasks: LiveData<List<Tasks>>

    init {
        val tasksDao = TaskDatabase.getDatabaseInstance(application).myTasksDao()
        repository = TasksRepository(tasksDao)
        tasks = repository.getAllTasks()
    }

    fun getAllTasks(): LiveData<List<Tasks>>{
        return tasks
    }

    fun insertTask(taskTitle:String,taskDescription:String,taskTime:Long,isRunning:Boolean){
        CoroutineScope(IO).launch {
            repository.insertTask(Tasks(0,taskTitle,taskDescription,0, false))

        }
    }

    fun updateTask(task:Tasks){
        CoroutineScope(IO).launch {

            repository.insertTask(task)
        }
    }

    fun deleteTask(taskId: Int){
        CoroutineScope(IO).launch {
            repository.deleteTasks(taskId)
        }
    }




//    @PrimaryKey(autoGenerate = true)
//    var id: Int ,
//    var title: String,
//    var description: String,
//    var taskTime: Long,
//    var isRunning : Boolean




}