package com.gnarledrootsystems.onedayto

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.gnarledrootsystems.onedayto.databinding.ActivityMainBinding
import com.gnarledrootsystems.onedayto.databinding.FragmentHourBinding
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import com.gnarledrootsystems.onedayto.model.HourTaskItem
import kotlinx.android.synthetic.main.fragment_hour_list.view.*
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HourFragment : Fragment() {

    private var columnCount = 8
    private lateinit var dateString: String
    private lateinit var binding: FragmentHourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            dateString = it.getString(ARG_DATE_STRING).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hour_list, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Set up Default Tasks in the DB
        runBlocking {
            val db = Room.databaseBuilder(
                view.context,
                AppDatabase::class.java, "oneday-db"
            ).fallbackToDestructiveMigration()
                .build()

            val hourTaskItemDao = db.hourTaskItemDao()

            var default_id = 1000
            for (defaultTaskItem in HourBlockContent.DEFAULT_TASKS) {
                defaultTaskItem.uid = default_id
                hourTaskItemDao.insert(defaultTaskItem)
                default_id++
            }

        }

        var selected_date = LocalDate.now()
        if (dateString != "null") {
            selected_date = LocalDate.parse(dateString)
        }

        CurrentDay.insertAndRetrieveDay(requireContext(), selected_date.toString())

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            columnCount = 4
        } else {
            columnCount = 8
        }



        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = CurrentDay.getDay().editable_hours?.let { MyHourRecyclerViewAdapter(it) }
            }
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_DATE_STRING = "date_string"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                HourFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}