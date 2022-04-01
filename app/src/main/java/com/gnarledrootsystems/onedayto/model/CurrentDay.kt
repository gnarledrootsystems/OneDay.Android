package com.gnarledrootsystems.onedayto.model

import android.content.Context
import androidx.room.Room
import com.gnarledrootsystems.onedayto.AppDatabase
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

object CurrentDay {

    private var _selected_date: String = ""
    private var _day: Day = Day()

    fun getDay(): Day {
        return _day
    }

    fun getSelectedDate(): String {
        return _selected_date
    }

    fun insertAndRetrieveDay(context: Context, date: String) {
        runBlocking {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "oneday-db"
            ).fallbackToDestructiveMigration()
                .build()

            val dayDao = db.dayDao()

            val day = dayDao.findByDate(date)

            if (day == null) {
                _day.date = date
                _day.hours = HourBlockContent.default_hours_json()

                dayDao.insert(_day)
            } else {
                _day.date = day.date
                _day.hours = day.hours
            }

            _day.editable_hours = _day.json_hours_to_list()
            _selected_date = date
        }
    }

    fun updateDB(context: Context) {
        runBlocking {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "oneday-db"
            ).fallbackToDestructiveMigration()
                .build()

            val dayDao = db.dayDao()

            _day.list_to_json_hours()

            dayDao.update(_day)
        }
    }
}