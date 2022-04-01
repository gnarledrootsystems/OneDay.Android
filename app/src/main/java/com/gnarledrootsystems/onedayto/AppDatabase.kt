package com.gnarledrootsystems.onedayto

import androidx.room.migration.AutoMigrationSpec
import com.gnarledrootsystems.onedayto.dao.DayDao
import com.gnarledrootsystems.onedayto.dao.HourTaskItemDao
import com.gnarledrootsystems.onedayto.model.Day
import com.gnarledrootsystems.onedayto.model.HourTaskItem
import androidx.sqlite.db.SupportSQLiteDatabase

import androidx.annotation.NonNull
import androidx.room.*


/*@Database(
    entities = [Day::class],
    version = 1,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao
}*/

@Database(
    version = 5,
    entities = [Day::class, HourTaskItem::class],
    exportSchema = true,
    autoMigrations = [
        /*AutoMigration(
            from = 1,
            to = 2,
            spec = AppDatabase.AppDatabaseAutoMigration::class
        ),
        AutoMigration(
            from = 2,
            to = 3,
            spec = AppDatabase.AppDatabaseAutoMigration::class
        ),
        AutoMigration(
            from = 3,
            to = 4,
            spec = AppDatabase.AppDatabaseAutoMigration::class
        ),*/
        AutoMigration(
            from = 4,
            to = 5,
            spec = AppDatabase.AppDatabaseAutoMigration::class
        ),
])
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao
    abstract fun hourTaskItemDao(): HourTaskItemDao

    //@DeleteTable.Entries(DeleteTable(tableName = "TaskItem"))
    class AppDatabaseAutoMigration: AutoMigrationSpec { }
}