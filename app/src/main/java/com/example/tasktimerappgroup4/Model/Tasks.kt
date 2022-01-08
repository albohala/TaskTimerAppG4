package com.example.tasktimerappgroup4.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
class Tasks (
    @PrimaryKey(autoGenerate = true)
    var id: Int ,
    var title: String,
    var description: String,
    var taskTime: Long,
    var isRunning : Boolean


)