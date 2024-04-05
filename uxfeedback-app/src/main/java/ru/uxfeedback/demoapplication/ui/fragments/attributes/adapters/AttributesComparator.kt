package ru.uxfeedback.demoapplication.ui.fragments.attributes.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord

class AttributesComparator : DiffUtil.ItemCallback<AttributeRecord>() {

    override fun areItemsTheSame(oldItem: AttributeRecord, newItem: AttributeRecord): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: AttributeRecord, newItem: AttributeRecord): Boolean {
        return oldItem == newItem
    }

}