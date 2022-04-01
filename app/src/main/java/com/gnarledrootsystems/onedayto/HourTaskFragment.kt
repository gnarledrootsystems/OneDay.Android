package com.gnarledrootsystems.onedayto

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.room.Room
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import com.gnarledrootsystems.onedayto.model.HourTaskItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_hour_task_list.view.*
import kotlinx.coroutines.runBlocking

/**
 * A fragment representing a list of Items.
 */
class HourTaskFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_settings).isVisible = false
        menu.findItem(R.id.action_settings).isEnabled = false

        menu.findItem(R.id.action_calendar).isVisible = false
        menu.findItem(R.id.action_calendar).isEnabled = false

        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hour_task_list, container, false)
        setHasOptionsMenu(true)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view.fABAddTaskItem.setOnClickListener { _ ->
            val action = HourTaskFragmentDirections.actionHourTaskFragmentToCustomHourTaskFragment()
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(action)
        }

        val hourTaskItems: MutableList<HourTaskItem>

        runBlocking {
            val db = Room.databaseBuilder(
                view.context,
                AppDatabase::class.java, "oneday-db"
            ).fallbackToDestructiveMigration()
                .build()

            val hourTaskItemDao = db.hourTaskItemDao()

            hourTaskItems = hourTaskItemDao.getAll()
        }

        val allHourTaskItems: MutableList<HourTaskItem> = hourTaskItems

        allHourTaskItems.removeAll { it ->
            it.isDeleted
        }

        // Set the adapter
        if (view.listTaskItems is RecyclerView) {
            with(view.listTaskItems) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyHourTaskRecyclerViewAdapter(allHourTaskItems)
            }
        }

        return view
    }

    companion object {
        fun newInstance() = HourTaskFragment()
    }
}