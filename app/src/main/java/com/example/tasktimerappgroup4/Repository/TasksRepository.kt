package com.example.tasktimerappgroup4.Repository

import androidx.lifecycle.LiveData
import com.example.tasktimerappgroup4.TasksDao
import com.example.tasktimerappgroup4.Model.Tasks


class TasksRepository(val dao: TasksDao) {
    fun getAllTasks(): LiveData<List<Tasks>> {
        return dao.getTasks()
    }

    fun insertTask(tasks: Tasks){
        dao.insertTask(tasks)
    }
    fun deleteTasks(id : Int){
        dao.deleteTask(id)
    }
    fun updateTasks(tasks: Tasks){
        dao.updateTask(tasks)
    }
}