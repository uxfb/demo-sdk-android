package ru.uxfeedback.demoapplication.ui.fragments.options.bindings

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.ui.fragments.options.OptionsFragment

@BindingAdapter("setupThemeAdapter")
fun setupThemeAdapter(view: AutoCompleteTextView, sharedPreferencesApi: SharedPreferencesApi) {
    view.setText(sharedPreferencesApi.theme, false)
    view.setAdapter(ArrayAdapter(view.context, android.R.layout.simple_dropdown_item_1line, sharedPreferencesApi.getThemes()))
}

@BindingAdapter("setupThemeItemClickListener")
fun setupThemeItemClickListener(view: AutoCompleteTextView, bindingHolder: OptionsFragment.BindingHolder) {
    view.setOnItemClickListener { parent, view, position, id ->
        bindingHolder.choiceTheme(position)
    }
}

@BindingAdapter("setupApiUrlAdapter")
fun setupApiUrlAdapter(view: AutoCompleteTextView, sharedPreferencesApi: SharedPreferencesApi) {
    view.setText(sharedPreferencesApi.apiUrl.name, false)
    view.setAdapter(ArrayAdapter(view.context, android.R.layout.simple_dropdown_item_1line, sharedPreferencesApi.getApiUrls().map { it.name }))
}

@BindingAdapter("setupApiUrlItemClickListener")
fun setupApiUrlItemClickListener(view: AutoCompleteTextView, bindingHolder: OptionsFragment.BindingHolder) {
    view.setOnItemClickListener { parent, view, position, id ->
        bindingHolder.choiceApiUrls(position)
    }
}

