package com.example.tasktimerappgroup4.Repository

import androidx.lifecycle.LiveData
import com.example.tasktimerappgroup4.Dao.TasksDao
import com.example.tasktimerappgroup4.Model.Tasks


class TasksRepository(val dao: TasksDao) {
    fun getAllTasks(): LiveData<List<Tasks>> {
        return dao.getNotes()
    }

    fun insertTasks(tasks: Tasks){
        dao.insertNotes(tasks)
    }
    fun deleteTasks(id : Int){
        dao.deleteNotes(id)
    }
    fun updateTasks(tasks: Tasks){
        dao.updateNotes(tasks)
    }
}