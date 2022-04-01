package com.gnarledrootsystems.onedayto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class HourTaskItem(
    @ColumnInfo(name = "color") var color: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "display_order") var display_order: Int,
    @ColumnInfo(name = "is_hidden") var isHidden: Boolean,
    @ColumnInfo(name = "is_deleted") var isDeleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

}