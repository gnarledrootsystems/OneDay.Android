package com.gnarledrootsystems.onedayto

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.gnarledrootsystems.onedayto.placeholder.PlaceholderContent.PlaceholderItem
import com.gnarledrootsystems.onedayto.databinding.FragmentHourBinding
import com.gnarledrootsystems.onedayto.model.CurrentDay
import com.gnarledrootsystems.onedayto.model.HourBlockContent
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyHourRecyclerViewAdapter(
    private val values: MutableList<HourBlockContent.HourBlock>
)
    : RecyclerView.Adapter<MyHourRecyclerViewAdapter.ViewHolder>() {

    private var height: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        height = parent.measuredHeight / 6

        return ViewHolder(FragmentHourBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hour_block = values[position]

        holder.itemView.minimumHeight = height
        holder.itemView.setBackgroundColor(Color.parseColor(hour_block.task.color))
        holder.blockHour.text = hour_block.time
        holder.blockDescription.text = hour_block.task.description.uppercase()


        holder.itemView.setOnClickListener { view ->
            val selected_block_id = hour_block.id

            val current_task_id = HourBlockContent.DEFAULT_TASKS.indexOf(hour_block.task)
            val next_task_id = current_task_id + 1

            if (next_task_id < HourBlockContent.DEFAULT_TASKS.size) {
                hour_block.task = HourBlockContent.DEFAULT_TASKS.get(next_task_id)
            } else {
                hour_block.task = HourBlockContent.DEFAULT_TASKS.first()
            }

            CurrentDay.getDay().editable_hours?.get(selected_block_id)?.task = hour_block.task
            CurrentDay.updateDB(view.context)

            holder.itemView.setBackgroundColor(Color.parseColor(hour_block.task.color))
            holder.blockDescription.text = hour_block.task.description.uppercase()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHourBinding) : RecyclerView.ViewHolder(binding.root) {
        val blockHour: TextView = binding.blockHour
        val blockDescription: TextView = binding.blockDescription

        /*override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }*/
    }

}