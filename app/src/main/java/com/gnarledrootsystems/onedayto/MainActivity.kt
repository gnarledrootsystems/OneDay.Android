package com.gnarledrootsystems.onedayto

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.gnarledrootsystems.onedayto.databinding.ActivityMainBinding
import com.gnarledrootsystems.onedayto.model.CurrentDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyHourRecyclerViewAdapter

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
            val selected_date = LocalDate.parse(CurrentDay.getSelectedDate())

            val dpd = DatePickerDialog(this, { view, year, month, dayOfMonth ->
                val adjusted_month = month + 1

                var date_string = "$year-$adjusted_month-$dayOfMonth"
                if (dayOfMonth < 10) {
                    date_string = "$year-$adjusted_month-0$dayOfMonth"
                }

                var formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
                val date = LocalDate.parse(date_string).format(formatter)

                val actionBar = supportActionBar
                actionBar!!.subtitle = date.toString()

                val action = HourFragmentDirections.actionHourFragmentSelf(date_string)
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(action)

            }, selected_date.year, selected_date.monthValue - 1, selected_date.dayOfMonth)
            dpd.show()

            true
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }

}