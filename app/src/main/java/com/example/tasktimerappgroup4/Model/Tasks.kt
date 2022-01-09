package com.example.tasktimerappgroup4.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
class Tasks(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var taskTime: String,
    var totalTime: String,
    var isRunning: Boolean,
    var isClicked: Boolean,
    var pauseOffset: Long
)