package ru.uxfeedback.demoapplication.ui.common.interfaces

import ru.uxfeedback.pub.sdk.UxFbDimen

interface OnUxFbDimenClickListener {
    fun dimenClick(dimenName: String, dimen: UxFbDimen)
}