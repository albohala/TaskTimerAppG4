package com.example.tasktimerappgroup4

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasktimerappgroup4.Model.Tasks


@Dao
interface TasksDao {
    @Query("SELECT * FROM Tasks ")
    fun getNotes(): LiveData<List<Tasks>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(tasks: Tasks)

    @Query("Delete From Tasks WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(tasks: Tasks)

}