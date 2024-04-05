package ru.uxfeedback.demoapplication.ui.fragments.attributes.adapters

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.uxfeedback.demoapplication.databinding.LayoutAttributeRecordItemBinding
import ru.uxfeedback.demoapplication.ui.fragments.attributes.AttributesFragment
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord

class AttributesRecyclerViewAdapter(bindingHolder: AttributesFragment.BindingHolder) : ListAdapter<AttributeRecord, AttributesRecyclerViewAdapter.ViewHolder>(AttributesComparator())  {

    private val onEditClickListener = OnClickListener {
        (it.tag as Int?)?.let { pos->
            bindingHolder.editClick(currentList[pos])
        }
    }

    private val onDeleteClickListener = OnClickListener {
        (it.tag as Int?)?.let { pos->
            bindingHolder.deleteClick(currentList[pos].name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutAttributeRecordItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvKey.text = "Name: " + currentList[position].name
        holder.binding.tvValue.text = "Value: " + currentList[position].value + " (" + currentList[position].type + ")"
        holder.binding.btnDelete.tag = position
        holder.binding.root.tag = position
        holder.binding.root.setOnClickListener(onEditClickListener)
        holder.binding.btnDelete.setOnClickListener(onDeleteClickListener)
    }

    class ViewHolder(val binding: LayoutAttributeRecordItemBinding) : RecyclerView.ViewHolder(binding.root)

}