package ru.uxfeedback.demoapplication.ui.fragments.theme.bindings

import android.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import ru.uxfeedback.demoapplication.utils.applicationFonts
import ru.uxfeedback.demoapplication.utils.assetsFonts
import ru.uxfeedback.demoapplication.utils.fromAppFont
import ru.uxfeedback.demoapplication.utils.fromAssetsFont
import ru.uxfeedback.demoapplication.utils.systemFonts
import ru.uxfeedback.demoapplication.utils.toAppFont
import ru.uxfeedback.demoapplication.utils.toFontWeight
import ru.uxfeedback.pub.sdk.UxFbFont


@BindingAdapter("setupFontWeightAdapter" )
fun setupFontWeightAdapter(view: AutoCompleteTextView, fontValue: UxFbFont) {
    view.setText(fontValue.weight.toFontWeight(), false)
    val values = with(UxFbFont){
        mutableListOf(THIN, EXTRA_LIGHT, LIGHT, NORMAL, MEDIUM, SEMI_BOLD, BOLD, EXTRA_BOLD, BLACK)
    }
    view.setAdapter(ArrayAdapter(view.context, R.layout.simple_dropdown_item_1line, values.map { it.toFontWeight() }))
}

@BindingAdapter("setupFontAdapter" )
fun setupFontAdapter(view: AutoCompleteTextView, fontValue: UxFbFont) {
    view.setText(fontValue.name.fromAppFont().fromAssetsFont(), false)
    val values = mutableListOf<String>().apply {
        addAll(applicationFonts)
        addAll(systemFonts)
        addAll(assetsFonts)
    }
    view.setAdapter(ArrayAdapter(view.context, R.layout.simple_dropdown_item_1line, values))
}

