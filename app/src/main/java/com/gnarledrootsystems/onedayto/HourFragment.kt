package com.gnarledrootsystems.onedayto

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnarledrootsystems.onedayto.model.CurrentDay
import java.time.LocalDate

/**
 * A fragment representing a list of Items.
 */
class HourFragment : Fragment() {

    private var columnCount = 8
    private lateinit var dateString: String

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