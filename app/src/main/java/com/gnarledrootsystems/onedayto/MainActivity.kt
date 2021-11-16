package com.gnarledrootsystems.onedayto

import android.app.DatePickerDialog
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.room.Room
import com.gnarledrootsystems.onedayto.databinding.ActivityMainBinding
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.Day
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(findViewById(R.id.toolbar))



        var formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val date = LocalDate.now().format(formatter)

        val actionBar = supportActionBar
        actionBar!!.title = "OneDay.to"
        actionBar.subtitle = date
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {

            true
        }

        R.id.action_calendar -> {


            // DatePickerDialog months range from 0-11
            // LocalDate months range from 1-12
            // Calls adjusted accordingly to work together

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val adjusted_month = month + 1
                var formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
                val date = LocalDate.parse("$year-$adjusted_month-$dayOfMonth").format(formatter)

                val actionBar = supportActionBar
                actionBar!!.subtitle = date
            }, LocalDate.now().year, LocalDate.now().monthValue - 1, LocalDate.now().dayOfMonth)
            dpd.show()

            true
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }

}