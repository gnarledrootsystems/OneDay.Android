package com.gnarledrootsystems.onedayto

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnarledrootsystems.onedayto.dao.DayDao
import com.gnarledrootsystems.onedayto.model.Day

@Database(entities = [Day::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao
}