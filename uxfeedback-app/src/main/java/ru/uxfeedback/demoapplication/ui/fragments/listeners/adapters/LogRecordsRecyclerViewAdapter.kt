package ru.uxfeedback.demoapplication.ui.fragments.listeners.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.logs.LogManagerApi
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecord
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecordType
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecordType.CAMPAIGN
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecordType.LOG
import ru.uxfeedback.demoapplication.databinding.LayoutLogRecordItemBinding

class LogRecordsRecyclerViewAdapter: ListAdapter<LogRecord, LogRecordsRecyclerViewAdapter.ViewHolder>(LogRecordsComparator())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(LayoutLogRecordItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(currentList[position].type){
            CAMPAIGN -> holder.binding.root.setBackgroundResource(R.color.campaign_item)
            LOG -> holder.binding.root.background = null
        }
        holder.binding.tvDate.text = currentList[position].date
        holder.binding.tvMessage.text = currentList[position].message
    }

    class ViewHolder(val binding: LayoutLogRecordItemBinding) : RecyclerView.ViewHolder(binding.root)

}