package ru.uxfeedback.demoapplication.ui.fragments.attributes.interfaces

import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord

interface OnAttributeTypeChangeListener {
    fun onAttributeTypeChange(newAttributeRecord: AttributeRecord)
}