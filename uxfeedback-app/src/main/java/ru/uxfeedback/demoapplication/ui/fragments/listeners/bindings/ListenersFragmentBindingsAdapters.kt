package ru.uxfeedback.demoapplication.ui.fragments.listeners.bindings

import android.widget.LinearLayout.VERTICAL
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.uxfeedback.demoapplication.api.logs.LogManagerApi
import ru.uxfeedback.demoapplication.ui.fragments.listeners.adapters.LogRecordsRecyclerViewAdapter

@BindingAdapter("setupLogRecordsRecyclerView")
fun setupLogRecordsRecyclerView(view: RecyclerView, logManagerApi: LogManagerApi) {
    LogRecordsRecyclerViewAdapter().apply {
        view.adapter = this
        submitList(logManagerApi.logList)
    }
    view.addItemDecoration(DividerItemDecoration(view.context, VERTICAL))
}