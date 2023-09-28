package ru.uxfeedback.demoapplication.ui.common.bindings

import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.skydoves.colorpickerview.AlphaTileView
import ru.uxfeedback.pub.sdk.UxFbColor
import ru.uxfeedback.pub.sdk.UxFbFont

@BindingAdapter("onNavigationOnClickListener")
fun onNavigationOnClickListener(view: MaterialToolbar, callback: () -> Unit) {
    view.setNavigationOnClickListener { callback.invoke() }
}

@BindingAdapter("setPaintColor")
fun setPaintColor(view: AlphaTileView, color: UxFbColor) {
    view.setPaintColor(color.intValue)
}

@BindingAdapter("updateTypeface")
fun updateTypeface(view: TextView, font: UxFbFont) {
    view.typeface = font.wrap(view.typeface)
}

