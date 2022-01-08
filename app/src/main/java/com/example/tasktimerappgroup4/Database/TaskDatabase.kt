package com.example.tasktimerappgroup4.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasktimerappgroup4.Dao.TasksDao
import com.example.tasktimerappgroup4.Model.Tasks


@Database(entities = [Tasks::class], version = 2, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun myTasksDao(): TasksDao

    companion object {
        @Volatile
        var INSTANCE: TaskDatabase? = null

        fun getDatabaseInstance(context: Context): TaskDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {  // protection from concurrent execution on multiple threads
                val roomDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext, TaskDatabase::class.java, "Tasks"
                )  .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}