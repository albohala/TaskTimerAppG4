package com.example.tasktimerappgroup4.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasktimerappgroup4.Model.Tasks


@Dao
interface TasksDao {
    @Query("SELECT * FROM Tasks ")
    fun getTasks(): LiveData<List<Tasks>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(tasks: Tasks)

    @Query("Delete From Tasks WHERE id=:id")
    fun deleteTask(id:Int)

    @Update
    fun updateTask(tasks: Tasks)

    @Query("UPDATE Tasks SET taskTime = :taskTime WHERE id = :givenId")
    fun updateTaskTime(taskTime: Long, givenId: Int)

    @Query("UPDATE Tasks SET isRunning = :status WHERE id = :givenId")
    fun updateTaskStatus(status: Boolean, givenId: Int)
}