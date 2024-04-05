package ru.uxfeedback.demoapplication.ui.fragments.properties.bindings

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.uxfeedback.demoapplication.ui.fragments.properties.PropertiesFragment
import ru.uxfeedback.demoapplication.ui.fragments.properties.adapters.PropertiesRecyclerViewAdapter
import ru.uxfeedback.pub.sdk.UxFeedback

@BindingAdapter("setupPropertiesRecyclerView", "bindingHolder" )
fun setupPropertiesRecyclerView(view: RecyclerView, uxFeedback: UxFeedback, bindingHolder: PropertiesFragment.BindingHolder) {
    PropertiesRecyclerViewAdapter(bindingHolder).apply {
        view.adapter = this
        submitList(uxFeedback.properties.entries.map { it.toPair() })
    }
    view.addItemDecoration(DividerItemDecoration(view.context, LinearLayout.VERTICAL))
}
