package ru.uxfeedback.demoapplication.ui.fragments.presenter.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.databinding.LayoutDropdownItemBinding


class ItemDeleteAdapter(private val context: Context, private val items: MutableList<String>, private val callback: (Int) -> Unit) : ArrayAdapter<String>(context, R.layout.layout_dropdown_item, items) {

    private val deleteClickListener = View.OnClickListener {
        callback.invoke((it.tag as Int))
        items.removeAt((it.tag as Int))
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView?.tag?.let {
            (it as LayoutDropdownItemBinding).run {
                btnDelete.tag = position
                tvItem.text = items[position]
                root
            }
        } ?: run {
            LayoutDropdownItemBinding.inflate(LayoutInflater.from(context)).run {
                tvItem.text = items[position]
                btnDelete.tag = position
                btnDelete.setOnClickListener(deleteClickListener)
                root.apply { tag = this@run }
            }
        }
    }
}