package com.example.tasktimerappgroup4

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

}