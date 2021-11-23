package com.gnarledrootsystems.onedayto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

object HourBlockContent {

    // Default Tasks
    var DEFAULT_TASKS: MutableList<TaskItem> = mutableListOf(
        TaskItem(0, "#FFFFFF", "", 0, false),
        TaskItem(1, "#57838D", "sleep", 1, false),
        TaskItem(2, "#F3BFB3", "work", 2, false),
        TaskItem(3, "#50B4D8", "chores", 3, false),
        TaskItem(4, "#CAB3C1", "study", 4, false),
        TaskItem(5, "#A7D9C9", "leisure", 5, false),
        TaskItem(6, "#D3C0F9", "other", 6, false),
    )

    // Default Day Layout
    var DEFAULT_HOURS: MutableList<HourBlock> = mutableListOf(
        HourBlock(0, "12 AM", DEFAULT_TASKS.first()),
        HourBlock(1, "1 AM", DEFAULT_TASKS.first()),
        HourBlock(2, "2 AM", DEFAULT_TASKS.first()),
        HourBlock(3, "3 AM", DEFAULT_TASKS.first()),
        HourBlock(4, "4 AM", DEFAULT_TASKS.first()),
        HourBlock(5, "5 AM", DEFAULT_TASKS.first()),
        HourBlock(6, "6 AM", DEFAULT_TASKS.first()),
        HourBlock(7, "7 AM", DEFAULT_TASKS.first()),
        HourBlock(8, "8 AM", DEFAULT_TASKS.first()),
        HourBlock(9, "9 AM", DEFAULT_TASKS.first()),
        HourBlock(10, "10 AM", DEFAULT_TASKS.first()),
        HourBlock(11, "11 AM", DEFAULT_TASKS.first()),
        HourBlock(12, "12 PM", DEFAULT_TASKS.first()),
        HourBlock(13, "1 PM", DEFAULT_TASKS.first()),
        HourBlock(14, "2 PM", DEFAULT_TASKS.first()),
        HourBlock(15, "3 PM", DEFAULT_TASKS.first()),
        HourBlock(16, "4 PM", DEFAULT_TASKS.first()),
        HourBlock(17, "5 PM", DEFAULT_TASKS.first()),
        HourBlock(18, "6 PM", DEFAULT_TASKS.first()),
        HourBlock(19, "7 PM", DEFAULT_TASKS.first()),
        HourBlock(20, "8 PM", DEFAULT_TASKS.first()),
        HourBlock(21, "9 PM", DEFAULT_TASKS.first()),
        HourBlock(22, "10 PM", DEFAULT_TASKS.first()),
        HourBlock(23, "11 PM", DEFAULT_TASKS.first()),
    )

    fun default_hours_json(): String {
        return Json.encodeToString(DEFAULT_HOURS)
    }

    @Serializable
    data class HourBlock(
        var id: Int,
        var time: String,
        var task: TaskItem)

    @Entity
    @Serializable
    data class TaskItem(
        @PrimaryKey var uid: Int,
        @ColumnInfo(name = "color") var color: String,
        @ColumnInfo(name = "description") var description: String,
        @ColumnInfo(name = "order") var order: Int,
        @ColumnInfo(name = "is_hidden") var isHidden: Boolean
    )
}