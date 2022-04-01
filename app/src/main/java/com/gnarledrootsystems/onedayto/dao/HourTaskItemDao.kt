package com.gnarledrootsystems.onedayto.dao

import androidx.room.*
import com.gnarledrootsystems.onedayto.model.HourTaskItem

@Dao
interface HourTaskItemDao {
    @Query("SELECT * FROM HourTaskItem")
    suspend fun getAll(): MutableList<HourTaskItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hourTaskItem: HourTaskItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(hourTaskItem: HourTaskItem)

    @Delete
    suspend fun delete(hourTaskItem: HourTaskItem)
}