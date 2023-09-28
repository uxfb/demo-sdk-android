package ru.uxfeedback.demoapplication.ui.fragments.presenter.bindings


import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.LayoutDropdownItemBinding
import ru.uxfeedback.demoapplication.ui.fragments.presenter.adapters.ItemDeleteAdapter
import ru.uxfeedback.demoapplication.ui.fragments.presenter.PresenterFragment


@BindingAdapter("setupEventNameAdapter")
fun setupEventNameAdapter(view: AutoCompleteTextView, sharedPreferencesApi: SharedPreferencesApi) {
    view.setText(sharedPreferencesApi.eventName, false)
    view.setAdapter(ItemDeleteAdapter(view.context, sharedPreferencesApi.getEventNames().toMutableList()){ position ->
        sharedPreferencesApi.deleteEventName(position)
        view.dismissDropDown()
    })
}

@BindingAdapter("setupAppIdAdapter")
fun setupAppIdAdapter(view: AutoCompleteTextView, sharedPreferencesApi: SharedPreferencesApi) {
    view.setText(sharedPreferencesApi.appId, false)
    view.setAdapter(ItemDeleteAdapter(view.context, sharedPreferencesApi.getAppIds().toMutableList()){ position ->
        sharedPreferencesApi.deleteAppId(position)
        view.dismissDropDown()
    })
}

@BindingAdapter("setupAppIdItemClickListener")
fun setupAppIdItemClickListener(view: AutoCompleteTextView, bindingHolder: PresenterFragment.BindingHolder) {
    view.setOnItemClickListener { parent, view, position, id ->
        bindingHolder.saveAppIdClick((view.tag as LayoutDropdownItemBinding).tvItem.text.toString())
    }
}

@BindingAdapter("setupEventNameClickListener")
fun setupEventNameClickListener(view: AutoCompleteTextView, bindingHolder: PresenterFragment.BindingHolder) {
    view.setOnItemClickListener { parent, view, position, id ->
        bindingHolder.saveEventIdClick((view.tag as LayoutDropdownItemBinding).tvItem.text.toString())
    }
}