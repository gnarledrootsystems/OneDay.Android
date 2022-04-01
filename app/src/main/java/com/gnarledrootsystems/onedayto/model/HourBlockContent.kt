package com.gnarledrootsystems.onedayto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

object HourBlockContent {
    // Empty Task
    var EMPTY_TASK: MutableList<HourTaskItem> = mutableListOf(
        HourTaskItem(-1, "", 0, false, false)
    )

    // Default Tasks
    var DEFAULT_TASKS: MutableList<HourTaskItem> = mutableListOf(
        HourTaskItem(-11041907, "sleep", 1, false, false),
        HourTaskItem(-802893, "work", 2, false, false),
        HourTaskItem(-11488040, "chores", 3, false, false),
        HourTaskItem(-3492927, "study", 4, false, false),
        HourTaskItem(-5776951, "leisure", 5, false, false),
        HourTaskItem(-2899719, "other", 6, false, false),
    )

    // Default Day Layout
    var DEFAULT_HOURS: MutableList<HourBlock> = mutableListOf(
        HourBlock(0, "12 AM", EMPTY_TASK.first()),
        HourBlock(1, "1 AM", EMPTY_TASK.first()),
        HourBlock(2, "2 AM", EMPTY_TASK.first()),
        HourBlock(3, "3 AM", EMPTY_TASK.first()),
        HourBlock(4, "4 AM", EMPTY_TASK.first()),
        HourBlock(5, "5 AM", EMPTY_TASK.first()),
        HourBlock(6, "6 AM", EMPTY_TASK.first()),
        HourBlock(7, "7 AM", EMPTY_TASK.first()),
        HourBlock(8, "8 AM", EMPTY_TASK.first()),
        HourBlock(9, "9 AM", EMPTY_TASK.first()),
        HourBlock(10, "10 AM", EMPTY_TASK.first()),
        HourBlock(11, "11 AM", EMPTY_TASK.first()),
        HourBlock(12, "12 PM", EMPTY_TASK.first()),
        HourBlock(13, "1 PM", EMPTY_TASK.first()),
        HourBlock(14, "2 PM", EMPTY_TASK.first()),
        HourBlock(15, "3 PM", EMPTY_TASK.first()),
        HourBlock(16, "4 PM", EMPTY_TASK.first()),
        HourBlock(17, "5 PM", EMPTY_TASK.first()),
        HourBlock(18, "6 PM", EMPTY_TASK.first()),
        HourBlock(19, "7 PM", EMPTY_TASK.first()),
        HourBlock(20, "8 PM", EMPTY_TASK.first()),
        HourBlock(21, "9 PM", EMPTY_TASK.first()),
        HourBlock(22, "10 PM", EMPTY_TASK.first()),
        HourBlock(23, "11 PM", EMPTY_TASK.first()),
    )

    fun default_hours_json(): String {
        return Json.encodeToString(DEFAULT_HOURS)
    }

    @Serializable
    data class HourBlock(
        var id: Int,
        var time: String,
        var task: HourTaskItem)
}