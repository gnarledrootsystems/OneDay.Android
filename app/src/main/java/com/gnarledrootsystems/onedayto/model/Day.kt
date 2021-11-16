package com.gnarledrootsystems.onedayto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity
data class Day(
    @PrimaryKey var date: String = "",
    @ColumnInfo(name = "hours") var hours: String = "",
    @Ignore var editable_hours: MutableList<HourBlockContent.HourBlock>? = null
) {
    fun json_hours_to_list(): MutableList<HourBlockContent.HourBlock> {
       return Json.decodeFromString(this.hours)
    }

    fun list_to_json_hours() {
        hours = Json.encodeToString(editable_hours)
    }
}
