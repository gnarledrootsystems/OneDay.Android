package com.gnarledrootsystems.onedayto

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.children
import androidx.navigation.Navigation
import com.gnarledrootsystems.onedayto.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(findViewById(R.id.toolbar))

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val date = LocalDate.parse("$year-$month-$day")
        val formatted_date = date.format(formatter)

        val actionBar = supportActionBar
        actionBar!!.title = "OneDay.to"
        actionBar.subtitle = formatted_date
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

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                //textView.setText("" + dayOfMonth + " " + month + ", " + year)
                val date = "" + dayOfMonth + " " + month + ", " + year
            }, year, month, day)
            dpd.show()

            true
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }

}