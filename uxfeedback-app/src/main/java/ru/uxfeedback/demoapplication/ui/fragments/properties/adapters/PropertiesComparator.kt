package ru.uxfeedback.demoapplication.ui.fragments.properties.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.uxfeedback.demoapplication.ui.fragments.properties.entities.PropertyRecord

class PropertiesComparator : DiffUtil.ItemCallback<PropertyRecord>() {

    override fun areItemsTheSame(oldItem: PropertyRecord, newItem: PropertyRecord): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(oldItem: PropertyRecord, newItem: PropertyRecord): Boolean {
        return oldItem == newItem
    }

}