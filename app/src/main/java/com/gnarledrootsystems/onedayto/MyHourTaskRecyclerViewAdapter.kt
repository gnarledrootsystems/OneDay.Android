package com.gnarledrootsystems.onedayto

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.room.Room

import com.gnarledrootsystems.onedayto.databinding.FragmentHourTaskBinding
import com.gnarledrootsystems.onedayto.model.HourTaskItem
import kotlinx.coroutines.runBlocking

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyHourTaskRecyclerViewAdapter(
    private val values: MutableList<HourTaskItem>
) : RecyclerView.Adapter<MyHourTaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentHourTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskItem = values[position]

        //val color_int = Color.parseColor(taskItem.color)

        holder.taskColorChoice.setBackgroundColor(taskItem.color)
        holder.taskColorChoice.text = taskItem.description

        holder.editTaskName.text = taskItem.description
        holder.switchTaskVisible.isChecked = !taskItem.isHidden

        holder.buttonDelete.setOnClickListener { view ->
            taskItem.isDeleted = true

            runBlocking {
                val db = Room.databaseBuilder(
                    holder.itemView.context,
                    AppDatabase::class.java, "oneday-db"
                ).fallbackToDestructiveMigration()
                    .build()

                val hourTaskItemDao = db.hourTaskItemDao()

                hourTaskItemDao.update(taskItem)
            }

            /*runBlocking {
                val db = Room.databaseBuilder(
                    view.context,
                    AppDatabase::class.java, "oneday-db"
                ).fallbackToDestructiveMigration()
                    .build()

                val hourTaskItemDao = db.hourTaskItemDao()

                hourTaskItemDao.delete(taskItem)
            }*/

            notifyItemRemoved(position)
            values.remove(taskItem)

        }

        holder.switchTaskVisible.setOnCheckedChangeListener { compoundButton, isVisible ->
            // b == false \\ if the switch is off
            // b == true \\ if the switch is on

            taskItem.isHidden = !isVisible


            runBlocking {
                val db = Room.databaseBuilder(
                    holder.itemView.context,
                    AppDatabase::class.java, "oneday-db"
                ).fallbackToDestructiveMigration()
                    .build()

                val hourTaskItemDao = db.hourTaskItemDao()

                hourTaskItemDao.update(taskItem)
            }

        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHourTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        val taskColorChoice = binding.taskColorChoice
        val editTaskName = binding.editTaskName
        val switchTaskVisible = binding.switchTaskVisible
        val buttonDelete = binding.buttonDelete
        //val buttonTaskSoftDelete = binding.buttonTaskSoftDelete
    }

}