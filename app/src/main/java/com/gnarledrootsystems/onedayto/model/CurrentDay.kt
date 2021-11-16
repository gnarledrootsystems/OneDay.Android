package com.gnarledrootsystems.onedayto.model

import android.content.Context
import androidx.room.Room
import com.gnarledrootsystems.onedayto.AppDatabase
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

object CurrentDay {

    private lateinit var _selected_date: String
    private lateinit var _day: Day

    fun setDefaults() {
        _selected_date = LocalDate.now().toString()
        _day = Day(
            _selected_date,
            HourBlockContent.default_hours_json(),
            HourBlockContent.DEFAULT_HOURS
        )
    }

    fun getDay(): Day {
        return _day
    }

    fun getSelectedDate(): String {
        return _selected_date
    }

    fun insertAndRetrieveDay(context: Context, date: String? = null) {
        runBlocking {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "oneday-db"
            ).build()

            val dayDao = db.dayDao()

            if (date == null) {
                setDefaults()
            } else {
                _day.date = date
            }

            dayDao.insert(_day)
            _day = dayDao.findByDate(_day.date)

            _day.editable_hours = _day.json_hours_to_list()
        }
    }

    fun updateDB(context: Context) {
        runBlocking {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "oneday-db"
            ).build()

            val dayDao = db.dayDao()

            _day.list_to_json_hours()

            dayDao.update(_day)
        }
    }
}