package com.gnarledrootsystems.onedayto.model

object HourBlockContent {

    // Default Tasks
    var TASKS: MutableList<TaskItem> = mutableListOf(
        TaskItem(0, "#FFFFFF", ""),
        TaskItem(1, "#57838D", "sleep"),
        TaskItem(2, "#F3BFB3", "work work"),
        TaskItem(3, "#50B4D8", "chores"),
        TaskItem(4, "#CAB3C1", "studying"),
        TaskItem(5, "#A7D9C9", "leisure"),
        TaskItem(6, "#D3C0F9", "other"),
    )

    var HOURS: MutableList<HourBlock> = mutableListOf(
        HourBlock(0, "12 AM", TASKS.first()),
        HourBlock(1, "1 AM", TASKS.first()),
        HourBlock(2, "2 AM", TASKS.first()),
        HourBlock(3, "3 AM", TASKS.first()),
        HourBlock(4, "4 AM", TASKS.first()),
        HourBlock(5, "5 AM", TASKS.first()),
        HourBlock(6, "6 AM", TASKS.first()),
        HourBlock(7, "7 AM", TASKS.first()),
        HourBlock(8, "8 AM", TASKS.first()),
        HourBlock(9, "9 AM", TASKS.first()),
        HourBlock(10, "10 AM", TASKS.first()),
        HourBlock(11, "11 AM", TASKS.first()),
        HourBlock(12, "12 PM", TASKS.first()),
        HourBlock(13, "1 PM", TASKS.first()),
        HourBlock(14, "2 PM", TASKS.first()),
        HourBlock(15, "3 PM", TASKS.first()),
        HourBlock(16, "4 PM", TASKS.first()),
        HourBlock(17, "5 PM", TASKS.first()),
        HourBlock(18, "6 PM", TASKS.first()),
        HourBlock(19, "7 PM", TASKS.first()),
        HourBlock(20, "8 PM", TASKS.first()),
        HourBlock(21, "9 PM", TASKS.first()),
        HourBlock(22, "10 PM", TASKS.first()),
        HourBlock(23, "11 PM", TASKS.first()),
    )

    data class HourBlock(var id: Int, var time: String, var task: TaskItem)
    data class TaskItem(var id: Int, var color: String, var description: String)

}