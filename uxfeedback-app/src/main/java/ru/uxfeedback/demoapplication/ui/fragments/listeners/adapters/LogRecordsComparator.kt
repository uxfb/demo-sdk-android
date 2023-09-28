package ru.uxfeedback.demoapplication.ui.fragments.listeners.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecord

class LogRecordsComparator : DiffUtil.ItemCallback<LogRecord>() {

    override fun areItemsTheSame(oldItem: LogRecord, newItem: LogRecord): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: LogRecord, newItem: LogRecord): Boolean {
        return oldItem == newItem
    }

}
