package com.gnarledrootsystems.onedayto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.Navigation
import com.gnarledrootsystems.onedayto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {

            true
        }

        R.id.action_calendar -> {

            true
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }
}