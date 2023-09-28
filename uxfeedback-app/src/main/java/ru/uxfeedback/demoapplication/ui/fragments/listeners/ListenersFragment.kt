package ru.uxfeedback.demoapplication.ui.fragments.listeners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.logs.LogManagerApi
import ru.uxfeedback.demoapplication.api.logs.entities.LogRecord
import ru.uxfeedback.demoapplication.databinding.FragmentListenersBinding
import ru.uxfeedback.demoapplication.ui.fragments.listeners.adapters.LogRecordsRecyclerViewAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ListenersFragment: Fragment(R.layout.fragment_listeners) {

    @Inject
    lateinit var logManagerApi: LogManagerApi

    private lateinit var binding: FragmentListenersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentListenersBinding.inflate(layoutInflater).apply {
            logManagerApi = this@ListenersFragment.logManagerApi
            BindingHolder().apply {
                this@ListenersFragment.logManagerApi.subscribeUpdates(this)
                bindingHolder = this
            }
            binding = this
        }.root
    }

    inner class BindingHolder{

        fun resetLog()= logManagerApi.reset()

        fun onUpdate(items: List<LogRecord>){
            activity?.runOnUiThread {
                binding.recyclerView.adapter?.let {
                    (it as LogRecordsRecyclerViewAdapter).submitList(items)
                }
            }
        }
    }

}