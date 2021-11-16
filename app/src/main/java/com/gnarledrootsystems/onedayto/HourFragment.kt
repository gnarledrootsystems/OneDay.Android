package com.gnarledrootsystems.onedayto

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import androidx.room.RoomSQLiteQuery
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.Day
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import com.gnarledrootsystems.onedayto.placeholder.PlaceholderContent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.time.LocalDate

/**
 * A fragment representing a list of Items.
 */
class HourFragment : Fragment() {

    private var columnCount = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hour_list, container, false)

        // Decode json string back to List of HourBlocks
        // val json_rev = Json.decodeFromString<MutableList<HourBlockContent.HourBlock>>(json)

        //CurrentDay.insertAndRetrieveDay(requireContext(), LocalDate.now().toString())
        // Initialize CurrentDay Singleton
        CurrentDay.insertAndRetrieveDay(requireContext())

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