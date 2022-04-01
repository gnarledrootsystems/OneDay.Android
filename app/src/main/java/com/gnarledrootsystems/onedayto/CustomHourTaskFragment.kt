package com.gnarledrootsystems.onedayto

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.room.Room
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import com.gnarledrootsystems.onedayto.model.HourTaskItem
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_custom_hour_task.view.*
import kotlinx.coroutines.runBlocking
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog
import java.util.*

class CustomHourTaskFragment : Fragment() {
    private lateinit var hourTaskItem: HourTaskItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        hourTaskItem = HourTaskItem(
            Color.LTGRAY,
            "",
            0,
            false,
            false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_custom_hour_task, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        //  create dialog


        view.buttonColorPicker.setOnClickListener { _ ->
            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.WHITE)
                .setColorModel(ColorModel.RGB)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected { color: Int ->
                    view.textSelectedColor.setBackgroundColor(color)
                    hourTaskItem.color = color
                }
                .create()

            colorPicker.show(childFragmentManager, "color_picker")
        }

        view.buttonCancel.setOnClickListener { _ ->
            val action = CustomHourTaskFragmentDirections.actionCustomHourTaskFragmentToHourTaskFragment()
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(action)
        }

        view.buttonSave.setOnClickListener { _ ->
            hourTaskItem.description = view.editTextTaskName.text.toString()

            runBlocking {
                val db = Room.databaseBuilder(
                    view.context,
                    AppDatabase::class.java, "oneday-db"
                ).fallbackToDestructiveMigration()
                    .build()

                val hourTaskItemDao = db.hourTaskItemDao()

                hourTaskItemDao.insert(hourTaskItem)
            }

            val action = CustomHourTaskFragmentDirections.actionCustomHourTaskFragmentToHourTaskFragment()
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(action)
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance() = CustomHourTaskFragment()
    }
}