package ru.uxfeedback.demoapplication.ui.fragments.attributes.bindings

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.ui.fragments.attributes.AttributesFragment
import ru.uxfeedback.demoapplication.ui.fragments.attributes.adapters.AttributesRecyclerViewAdapter
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeType
import ru.uxfeedback.demoapplication.ui.fragments.attributes.interfaces.OnAttributeTypeChangeListener

@BindingAdapter("setupPropertiesRecyclerView", "bindingHolder" )
fun setupPropertiesRecyclerView(view: RecyclerView, attributes: List<AttributeRecord>, bindingHolder: AttributesFragment.BindingHolder) {
    AttributesRecyclerViewAdapter(bindingHolder).apply {
        view.adapter = this
        submitList(attributes)
    }
    view.addItemDecoration(DividerItemDecoration(view.context, LinearLayout.VERTICAL))
}


@BindingAdapter("setupAttributeTypeAdapter" )
fun setupAttributeTypeAdapter(view: MaterialAutoCompleteTextView, attribute: AttributeRecord) {
    view.setText(attribute.type.name, false)
    AttributeType.values().map { it.name }
    view.setAdapter(ArrayAdapter(view.context, android.R.layout.simple_dropdown_item_1line, AttributeType.values().map { it.name }))
}

@BindingAdapter("setupAttributeChangeListener", "nameTextInputEditText")
fun setupAttributeChangeListener(view: MaterialAutoCompleteTextView, listener: OnAttributeTypeChangeListener,  nameTextInputEditText: TextInputEditText) {
    view.setOnItemClickListener { adapterView, view, i, l ->
        listener.onAttributeTypeChange(AttributeRecord(nameTextInputEditText.text.toString(),AttributeType.values()[i], "" ))
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("setupAttributeDateButton")
fun setupAttributeDateButton(view: MaterialButton, attribute: AttributeRecord) {
    when (attribute.type){
        AttributeType.DATE -> {
            view.isVisible = true
            if (attribute.value.isEmpty()){
                view.setText(R.string.select_date)
                view.tag = null
            }else{
                view.tag = true
                view.text = attribute.value
            }
        }
       else ->{
           view.isGone = true
       }
    }
    view.setOnClickListener {
        DatePickerDialog(it.context).apply {
            setOnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                view.text = year.toString() + "-" +  String.format("%02d", monthOfYear + 1)+ "-"+ String.format("%02d", dayOfMonth)
                view.tag = true
            }
            show()
        }
    }
}


@BindingAdapter("setupAttributeBooleanCheckBox")
fun setupAttributeBooleanCheckBox(view: MaterialCheckBox, attribute: AttributeRecord) {
    when (attribute.type){
        AttributeType.BOOLEAN -> {
            view.isVisible = true
            view.isChecked = attribute.value == true.toString()
        }
        else ->{
            view.isGone = true
        }
    }
}


@BindingAdapter("setupAttributeValueTextInputLayout")
fun setupAttributeValueTextInputLayout(view: TextInputLayout, attribute: AttributeRecord) {
    when (attribute.type){
        AttributeType.STRING, AttributeType.DOUBLE,AttributeType.FLOAT,AttributeType.INT -> {
            view.isVisible = true
            view.editText?.setText(attribute.value)
        }
        else ->{
            view.isGone = true
        }
    }
}