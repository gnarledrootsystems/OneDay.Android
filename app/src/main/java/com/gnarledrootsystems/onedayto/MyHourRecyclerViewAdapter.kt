package com.gnarledrootsystems.onedayto

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
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

class MyHourRecyclerViewAdapter(
    private val values: MutableList<HourBlockContent.HourBlock>
)
    : RecyclerView.Adapter<MyHourRecyclerViewAdapter.ViewHolder>() {

    private var height: Int = 0
    private lateinit var hourTaskItems: List<HourTaskItem>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        height = parent.measuredHeight / 6

        runBlocking {
            val db = Room.databaseBuilder(
                parent.context,
                AppDatabase::class.java, "oneday-db"
            ).fallbackToDestructiveMigration()
                .build()

            val hourTaskItemDao = db.hourTaskItemDao()

            val hourTaskItemsFromDatabase = hourTaskItemDao.getAll()

            hourTaskItemsFromDatabase.removeAll { it ->
                it.isHidden
            }

            hourTaskItems = HourBlockContent.EMPTY_TASK + hourTaskItemsFromDatabase
            val test = "test"
        }

        return ViewHolder(FragmentHourBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hour_block = values[position]

        holder.itemView.minimumHeight = height
        holder.itemView.setBackgroundColor(hour_block.task.color)
        holder.blockHour.text = hour_block.time
        holder.blockDescription.text = hour_block.task.description.uppercase()




        holder.itemView.setOnClickListener { view ->
            val selected_block_id = hour_block.id

            val current_task_id = hourTaskItems.indexOf(hour_block.task)
            val next_task_id = current_task_id + 1

            if (next_task_id < hourTaskItems.size) {
                hour_block.task = hourTaskItems[next_task_id]
            } else {
                hour_block.task = hourTaskItems.first()
            }

            CurrentDay.getDay().editable_hours?.get(selected_block_id)?.task = hour_block.task
            CurrentDay.updateDB(view.context)

            holder.itemView.setBackgroundColor(hour_block.task.color)
            holder.blockDescription.text = hour_block.task.description.uppercase()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHourBinding) : RecyclerView.ViewHolder(binding.root) {
        val blockHour: TextView = binding.blockHour
        val blockDescription: TextView = binding.blockDescription
    }

}