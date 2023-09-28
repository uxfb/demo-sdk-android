package ru.uxfeedback.demoapplication.ui.fragments.properties.adapters

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.uxfeedback.demoapplication.databinding.LayoutPropertyRecordItemBinding
import ru.uxfeedback.demoapplication.ui.fragments.properties.PropertiesFragment
import ru.uxfeedback.demoapplication.ui.fragments.properties.entities.PropertyRecord

class PropertiesRecyclerViewAdapter(bindingHolder: PropertiesFragment.BindingHolder) : ListAdapter<PropertyRecord, PropertiesRecyclerViewAdapter.ViewHolder>(PropertiesComparator())  {

    private val onEditClickListener = OnClickListener {
        (it.tag as Int?)?.let { pos->
            bindingHolder.editClick(currentList[pos])
        }
    }

    private val onDeleteClickListener = OnClickListener {
        (it.tag as Int?)?.let { pos->
            bindingHolder.deleteClick(currentList[pos].first)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutPropertyRecordItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvKey.text = currentList[position].first
        holder.binding.tvValue.text = currentList[position].second
        holder.binding.btnDelete.tag = position
        holder.binding.root.tag = position
        holder.binding.root.setOnClickListener(onEditClickListener)
        holder.binding.btnDelete.setOnClickListener(onDeleteClickListener)
    }

    class ViewHolder(val binding: LayoutPropertyRecordItemBinding) : RecyclerView.ViewHolder(binding.root)

}