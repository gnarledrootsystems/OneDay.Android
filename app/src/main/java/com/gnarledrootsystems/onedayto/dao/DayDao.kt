package com.gnarledrootsystems.onedayto.dao

import androidx.room.*
import com.gnarledrootsystems.onedayto.model.Day

@Dao
interface DayDao {
    @Query("SELECT * FROM day WHERE date LIKE :date LIMIT 1")
    suspend fun findByDate(date: String): Day?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(day: Day)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(day: Day)

    @Delete
    suspend fun delete(day: Day)
}